package com.example.splitandpay.backend.repository

import com.example.splitandpay.backend.model.db.Room
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface RoomRepository : MongoRepository<Room, UUID> {
    fun findAllByOwnerId(ownerId: ObjectId): List<Room>
}
