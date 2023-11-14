package com.example.splitandpay.backend.controller

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.model.db.User
import com.example.splitandpay.backend.model.dto.CreateUserRequest
import com.example.splitandpay.backend.model.dto.UpdateUserRequest
import com.example.splitandpay.backend.model.dto.UserDto
import com.example.splitandpay.backend.repository.UserRepository
import com.example.splitandpay.backend.utils.toDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/user")
class UserController(private val userRepository: UserRepository) {
    @PostMapping("/")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): UserDto {
        val newUser = User(name = createUserRequest.name)
        return userRepository.save(newUser).toDto()
    }

    @GetMapping("/get")
    fun getUser(@RequestParam id: String): UserDto {
        try {
            return userRepository.findById(UUID.fromString(id)).orElseThrow { ApiError.UserNotFound(id) }.toDto()
        } catch (e: IllegalArgumentException) {
            throw ApiError.InvalidUserId(id)
        }
    }

    @PutMapping
    fun updateUser(
        @RequestHeader userId: String,
        @RequestBody updateUserRequest: UpdateUserRequest
    ): UserDto {
        try {
            val user = userRepository.findById(UUID.fromString(userId)).orElseThrow { ApiError.UserNotFound(userId) }
            user.name = updateUserRequest.username
            return userRepository.save(user).toDto()
        } catch (e: IllegalArgumentException) {
            throw ApiError.InvalidUserId(userId)
        }
    }
}
