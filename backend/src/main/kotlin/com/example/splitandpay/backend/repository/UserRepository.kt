package com.example.splitandpay.backend.repository

import com.example.splitandpay.backend.model.db.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface UserRepository : MongoRepository<User, UUID>
