package com.example.splitandpay.backend.service

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.model.db.Product
import com.example.splitandpay.backend.model.db.Room
import com.example.splitandpay.backend.model.db.RoomCounter
import com.example.splitandpay.backend.model.db.User
import com.example.splitandpay.backend.model.dto.AddProductFromCheckRequest
import com.example.splitandpay.backend.model.dto.AddProductRequest
import com.example.splitandpay.backend.model.dto.CreateRoomRequest
import com.example.splitandpay.backend.model.dto.OwnerDto
import com.example.splitandpay.backend.model.dto.ProductDto
import com.example.splitandpay.backend.model.dto.RoomDto
import com.example.splitandpay.backend.model.dto.SumPerProductDto
import com.example.splitandpay.backend.model.dto.TotalSumForUserDto
import com.example.splitandpay.backend.model.dto.UserToProduct
import com.example.splitandpay.backend.repository.RoomRepository
import com.example.splitandpay.backend.repository.UserRepository
import com.example.splitandpay.backend.service.check.ProverkaCheckaService
import jakarta.annotation.PostConstruct
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime
import kotlin.math.roundToLong

@Service
class RoomService(
    private val roomRepository: RoomRepository,
    private val mongoOperations: MongoOperations,
    private val userRepository: UserRepository,
    private val proverkaCheckaService: ProverkaCheckaService
) {
    private lateinit var counterId: ObjectId

    @PostConstruct
    fun init() {
        val counters = mongoOperations.findAll(RoomCounter::class.java, "roomCounter")
        when (counters.size) {
            0 -> createNewCounter()
            1 -> counterId = counters.first().id
            in 2..Int.MAX_VALUE -> counters.drop(1).forEach {
                mongoOperations.remove(it, "roomCounter")
            }.also { counterId = counters.first().id }
        }
    }

    fun createRoom(userId: ObjectId, createRoomRequest: CreateRoomRequest): RoomDto {
        val counter = mongoOperations.findAndModify(
            Query.query(Criteria.where("_id").`is`(counterId)),
            Update().inc("counter", 1),
            RoomCounter::class.java,
            "roomCounter"
        ) ?: createNewCounter()
        val owner = OwnerDto(userId, userRepository.findById(userId).get().name)
        val newRoom =
            Room(counter.counter, createRoomRequest.name, owner, createdAt = getCurrentTime())
        return roomRepository.save(newRoom)
            .also {
                mongoOperations.findAndModify(
                    Query.query(Criteria.where("_id").`is`(userId)),
                    Update().push("rooms", it.id),
                    User::class.java,
                    "user"
                )
            }.toDto(userId)
    }

    private fun createNewCounter(): RoomCounter {
        return mongoOperations.save(
            RoomCounter(counter = roomRepository.findAll().lastOrNull()?.id ?: 1),
            "roomCounter"
        ).apply { counterId = id }
    }

    private fun getCurrentTime(): LocalDateTime {
        return LocalDateTime.now(Clock.systemUTC())
    }

    fun getRooms(partipiciantId: ObjectId): List<RoomDto> {
        return roomRepository.findAllByParticipantsContaining(partipiciantId).map {
            RoomDto(
                it.id,
                it.name,
                it.owner,
                it.createdAt
            )
        }
    }

    fun getRoom(roomId: Long, userId: ObjectId): RoomDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        return room.toDto(userId)
    }

    fun addProductsFromCheck(
        userId: ObjectId,
        roomId: Long,
        addProductFromCheckRequest: AddProductFromCheckRequest
    ): RoomDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        if (userId != room.owner.id && userId !in room.participants) {
            throw ApiError.AccessDenied
        }
        val checkDto = proverkaCheckaService.sendCheck(addProductFromCheckRequest.checkData)
        val productsSize = room.products.size
        checkDto.data.json.items.forEachIndexed { index, item ->
            room.products.add(Product(item.name, productsSize + index, (item.sum).toDouble() / 100))
        }
        return roomRepository.save(room).toDto(userId)
    }

    fun addProduct(userId: ObjectId, roomId: Long, addProductRequest: AddProductRequest): RoomDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        if (userId != room.owner.id && userId !in room.participants) {
            throw ApiError.AccessDenied
        }
        if (room.products.find { it.name == addProductRequest.name } != null) {
            throw ApiError.ProductAlreadyAdded(addProductRequest.name, addProductRequest.amount)
        }
        room.products.add(Product(addProductRequest.name, room.products.size, addProductRequest.amount))
        return roomRepository.save(room).toDto(userId)
    }

    private fun Room.toDto(userId: ObjectId): RoomDto {
        return RoomDto(
            id = id,
            name = name,
            owner = owner,
            createdAt = createdAt,
            users = userRepository.findAllById(participants).map { OwnerDto(it.id, it.name) },
            receipt = products.map { (name, id, amount, users) ->
                ProductDto(
                    name = name,
                    id = id,
                    amount = amount,
                    users = userRepository.findAllById(users).map { OwnerDto(it.id, it.name) })
            },
            totalSum = countTotalSumInternal(this, userId).total
        )
    }

    fun connectToRoom(userId: ObjectId, roomId: Long): RoomDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        if (userId in room.participants) {
            return room.toDto(userId)
        }
        room.participants.add(userId)
        return roomRepository.save(room).toDto(userId)
    }

    fun countTotalSumForUser(userId: ObjectId, roomId: Long): TotalSumForUserDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        if (userId != room.owner.id && userId !in room.participants) {
            throw ApiError.AccessDenied
        }
        return countTotalSumInternal(room, userId)
    }

    private fun countTotalSumInternal(room: Room, userId: ObjectId): TotalSumForUserDto {
        var total = 0.0
        val perProduct = room.products.filter { it.users.contains(userId) }
            .map { product ->
                SumPerProductDto(product.id, product.name, product.amount / product.users.size)
                    .also { total += it.sum }
            }
        return TotalSumForUserDto(userId, total.round(2), perProduct)
    }

    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return (this * multiplier).roundToLong() / multiplier
    }

    fun addOrDeleteUserToProductMapping(
        userId: ObjectId,
        roomId: Long,
        userToProduct: UserToProduct,
        add: Boolean
    ): RoomDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        if (userId != room.owner.id && userId !in room.participants) {
            throw ApiError.AccessDenied
        }
        if (userToProduct.userId != room.owner.id && userToProduct.userId !in room.participants) {
            throw ApiError.UserNotFound(userToProduct.userId.toHexString())
        }
        val product = room.products.find { it.id == userToProduct.productId }
            ?: throw ApiError.ProductNotFound(userToProduct.productId)
        if (add) {
            product.users.add(userToProduct.userId)
        } else {
            product.users.remove(userToProduct.userId)
        }
        return roomRepository.save(room).toDto(userId)
    }

    fun deleteRoom(userId: ObjectId, roomId: Long): List<RoomDto> {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        if (userId !in room.participants) {
            throw ApiError.AccessDenied
        }
        room.participants.remove(userId)
        room.products.forEach {
            if (userId in it.users) {
                it.users.remove(userId)
            }
        }
        roomRepository.save(room)
        return getRooms(userId)
    }
}
