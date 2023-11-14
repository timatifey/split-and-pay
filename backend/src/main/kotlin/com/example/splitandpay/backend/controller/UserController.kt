package com.example.splitandpay.backend.controller

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.model.User
import com.example.splitandpay.backend.model.dto.CreateUserRequest
import com.example.splitandpay.backend.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/user")
class UserController(private val userRepository: UserRepository) {
    @PostMapping("/")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): User {
        val newUser = User(name = createUserRequest.name)
        return userRepository.save(newUser)
    }

    @GetMapping("/get")
    fun getUser(@RequestParam id: String): User {
        try {
            return userRepository.findById(UUID.fromString(id)).orElseThrow { ApiError.UserNotFound(id) }
        } catch (e: IllegalArgumentException) {
            throw ApiError.InvalidUserId(id)
        }
    }
}
