package com.example.splitandpay.backend.repository

import com.example.splitandpay.backend.model.db.Room
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface RoomRepository : MongoRepository<Room, UUID>
