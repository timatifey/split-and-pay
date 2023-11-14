package com.example.splitandpay.backend.repository

import com.example.splitandpay.backend.model.db.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId>
