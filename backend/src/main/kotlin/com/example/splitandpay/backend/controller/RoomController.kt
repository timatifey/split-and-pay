package com.example.splitandpay.backend.controller

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.model.dto.AddProductRequest
import com.example.splitandpay.backend.model.dto.AddUserToProduct
import com.example.splitandpay.backend.model.dto.CreateRoomRequest
import com.example.splitandpay.backend.model.dto.CreateRoomResponse
import com.example.splitandpay.backend.model.dto.RoomDto
import com.example.splitandpay.backend.service.RoomService
import com.example.splitandpay.backend.utils.toObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/rooms")
class RoomController(
    private val roomService: RoomService
) {
    @GetMapping("/{roomId}")
    fun getRoom(
        @PathVariable roomId: Long
    ): RoomDto {
        return roomService.getRoom(roomId)
    }

    @GetMapping("/{roomId}/connect")
    fun connectToRoom(
        @RequestHeader userId: String,
        @PathVariable roomId: Long
    ): RoomDto {
        return roomService.connectToRoom(userId.toObjectId(), roomId)
    }

    @PostMapping("/{roomId}/addProduct")
    fun addProduct(
        @RequestHeader userId: String,
        @PathVariable roomId: Long,
        @RequestBody addProductRequest: AddProductRequest
    ): RoomDto {
        return roomService.addProduct(userId.toObjectId(), roomId, addProductRequest)
    }

    @PostMapping("/{roomId}/addUserToProduct")
    fun addUserToProduct(
        @RequestHeader userId: String,
        @PathVariable roomId: Long,
        @RequestBody addUserToProduct: AddUserToProduct
    ): RoomDto {
        return roomService.addUserToProduct(userId.toObjectId(), roomId, addUserToProduct)
    }

    @PostMapping("/")
    fun createRoom(
        @RequestHeader userId: String,
        @RequestBody createRoomRequest: CreateRoomRequest
    ): CreateRoomResponse {
        try {
            return roomService.createRoom(userId.toObjectId(), createRoomRequest)
        } catch (e: IllegalArgumentException) {
            throw ApiError.InvalidUserId(userId)
        }
    }

    @GetMapping("/")
    fun getRooms(
        @RequestHeader userId: String,
    ): List<RoomDto> {
        try {
            return roomService.getRooms(userId.toObjectId())
        } catch (e: IllegalArgumentException) {
            throw ApiError.InvalidUserId(userId)
        }
    }
}
