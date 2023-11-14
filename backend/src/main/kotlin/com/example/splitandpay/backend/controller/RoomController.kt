package com.example.splitandpay.backend.controller

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.model.Room
import com.example.splitandpay.backend.model.dto.CreateRoomRequest
import com.example.splitandpay.backend.repository.RoomRepository
import com.example.splitandpay.backend.utils.toUUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/rooms")
class RoomController(private val roomRepository: RoomRepository) {
    @GetMapping("/{roomId}")
    fun getRoom(@PathVariable roomId: String): Room {
        try {
            return roomRepository.findById(UUID.fromString(roomId)).orElseThrow { ApiError.RoomNotFound(roomId) }
        } catch (e: IllegalArgumentException) {
            throw ApiError.InvalidRoomId(roomId)
        }
    }

    @PostMapping("/")
    fun createRoom(
        @RequestHeader userId: String,
        @RequestBody createRoomRequest: CreateRoomRequest
    ): Room {
        try {
            val newRoom = Room(participants = mutableListOf(userId.toUUID()))
            return roomRepository.save(newRoom)
        } catch (e: IllegalArgumentException) {
            throw ApiError.InvalidUserId(userId)
        }
    }
}
