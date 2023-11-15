package com.example.splitandpay.backend.service

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.model.db.Product
import com.example.splitandpay.backend.model.db.Room
import com.example.splitandpay.backend.model.db.RoomCounter
import com.example.splitandpay.backend.model.dto.AddProductRequest
import com.example.splitandpay.backend.model.dto.AddUserToProduct
import com.example.splitandpay.backend.model.dto.CreateRoomRequest
import com.example.splitandpay.backend.model.dto.CreateRoomResponse
import com.example.splitandpay.backend.model.dto.OwnerDto
import com.example.splitandpay.backend.model.dto.ProductDto
import com.example.splitandpay.backend.model.dto.RoomDto
import com.example.splitandpay.backend.repository.RoomRepository
import com.example.splitandpay.backend.repository.UserRepository
import jakarta.annotation.PostConstruct
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime

@Service
class RoomService(
    private val roomRepository: RoomRepository,
    private val mongoOperations: MongoOperations,
    private val userRepository: UserRepository
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

    fun createRoom(userId: ObjectId, createRoomRequest: CreateRoomRequest): CreateRoomResponse {
        val counter = mongoOperations.findAndModify(
            Query.query(Criteria.where("_id").`is`(counterId)),
            Update().inc("counter", 1),
            RoomCounter::class.java,
            "roomCounter"
        ) ?: createNewCounter()
        val owner = OwnerDto(userId, userRepository.findById(userId).get().name)
        val newRoom =
            Room(counter.counter, createRoomRequest.roomName, owner, createdAt = getCurrentTime())
        return CreateRoomResponse(roomRepository.save(newRoom).id)
    }

    private fun createNewCounter(): RoomCounter {
        return mongoOperations.save(
            RoomCounter(counter = roomRepository.findAll().lastOrNull()?.id ?: 0),
            "roomCounter"
        ).apply { counterId = id }
    }

    private fun getCurrentTime(): LocalDateTime {
        return LocalDateTime.now(Clock.systemUTC())
    }

    fun getRooms(ownerId: ObjectId): List<RoomDto> {
        return roomRepository.findAllByOwnerId(ownerId).map {
            RoomDto(
                it.id,
                it.owner,
                it.createdAt
            )
        }
    }

    fun getRoom(roomId: Long): RoomDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        return room.toDto()
    }

    fun addProduct(userId: ObjectId, roomId: Long, addProductRequest: AddProductRequest): RoomDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        if (userId != room.owner.id && userId !in room.participants) {
            throw ApiError.AccessDenied
        }
        if (room.products.find { it.name == addProductRequest.name } != null) {
            throw ApiError.ProductAlreadyAdded(addProductRequest.name, addProductRequest.amount)
        }
        room.products.add(Product(addProductRequest.name, addProductRequest.amount))
        return roomRepository.save(room).toDto()
    }

    private fun Room.toDto(): RoomDto {
        return RoomDto(
            id = id,
            owner = owner,
            createdAt = createdAt,
            users = userRepository.findAllById(participants).map { OwnerDto(it.id, it.name) },
            receipt = products.map { (name, amount, users) ->
                ProductDto(
                    name = name,
                    amount = amount,
                    users = userRepository.findAllById(users).map { OwnerDto(it.id, it.name) })
            }
        )
    }

    fun addUserToProduct(userId: ObjectId, roomId: Long, addUserToProduct: AddUserToProduct): RoomDto {
        val room = roomRepository.findById(roomId).orElseThrow { ApiError.RoomNotFound(roomId) }
        if (userId != room.owner.id && userId !in room.participants) {
            throw ApiError.AccessDenied
        }
        if (addUserToProduct.userId != room.owner.id && addUserToProduct.userId !in room.participants) {
            throw ApiError.UserNotFound(addUserToProduct.userId.toHexString())
        }
        val product = room.products.find { it.name == addUserToProduct.productName }
            ?: throw ApiError.ProductNotFound(addUserToProduct.productName)
        product.users.add(addUserToProduct.userId)
        return roomRepository.save(room).toDto()
    }
}
