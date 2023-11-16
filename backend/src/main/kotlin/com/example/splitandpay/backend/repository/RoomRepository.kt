package com.example.splitandpay.backend.repository

import com.example.splitandpay.backend.model.db.Room
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface RoomRepository : MongoRepository<Room, Long> {

    fun findAllByParticipantsContaining(id: ObjectId): List<Room>
}
