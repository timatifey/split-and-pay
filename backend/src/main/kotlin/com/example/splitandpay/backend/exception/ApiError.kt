package com.example.splitandpay.backend.exception

import org.springframework.http.HttpStatus

sealed class ApiError(val code: HttpStatus, override val message: String) : Throwable() {
    data class InvalidUserId(val userId: String) : ApiError(HttpStatus.BAD_REQUEST, "User id $userId is invalid.")

    data class UserNotFound(val userId: String) : ApiError(HttpStatus.NOT_FOUND, "User with id $userId was not found.")

    data class RoomNotFound(val roomId: Long) : ApiError(HttpStatus.NOT_FOUND, "Room with id $roomId was not found.")

    data object AccessDenied : ApiError(HttpStatus.FORBIDDEN, "Access denied") {
        private fun readResolve(): Any = AccessDenied
    }

    data object ServiceUnavailable :
        ApiError(HttpStatus.SERVICE_UNAVAILABLE, "Service unavailable. Please retry later.") {
        private fun readResolve(): Any = ServiceUnavailable
    }

    data class ProductAlreadyAdded(val productName: String, val productAmount: Double) : ApiError(
        HttpStatus.BAD_REQUEST,
        "Product $productName - $productAmount was already added"
    )

    data class ProductNotFound(val id: Int) : ApiError(HttpStatus.NOT_FOUND, "Product with id $id was not found.")
}
