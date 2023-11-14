package com.example.splitandpay.backend.exception

import org.springframework.http.HttpStatus

sealed class ApiError(val code: HttpStatus, override val message: String) : Throwable() {
    data class InvalidUserId(val userId: String) : ApiError(HttpStatus.BAD_REQUEST, "User id $userId is invalid.")

    data class UserNotFound(val userId: String) : ApiError(HttpStatus.NOT_FOUND, "User with id $userId was not found.")

    data class InvalidRoomId(val roomId: String) : ApiError(HttpStatus.BAD_REQUEST, "Room id $roomId is invalid.")

    data class RoomNotFound(val roomId: String) : ApiError(HttpStatus.NOT_FOUND, "Room with id $roomId was not found.")
}
