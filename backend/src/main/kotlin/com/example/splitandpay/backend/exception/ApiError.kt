package com.example.splitandpay.backend.exception

import org.springframework.http.HttpStatus

sealed class ApiError(val code: HttpStatus, override val message: String) : Throwable() {
    data class InvalidUserId(val userId: String) : ApiError(HttpStatus.BAD_REQUEST, "User id $userId is invalid.")

    data class UserNotFound(val userId: String) : ApiError(HttpStatus.NOT_FOUND, "User with id $userId was not found.")

    data class RoomNotFound(val roomId: Long) : ApiError(HttpStatus.NOT_FOUND, "Room with id $roomId was not found.")

    object ServiceUnavailable : ApiError(HttpStatus.SERVICE_UNAVAILABLE, "Service unavailable. Please retry later.")
}
