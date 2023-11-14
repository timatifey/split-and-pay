package com.example.splitandpay.backend.exception

import com.example.splitandpay.backend.model.dto.ApiErrorDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalHandler {

    @ExceptionHandler(ApiError::class)
    fun handleApiError(exception: ApiError): ResponseEntity<ApiErrorDto> {
        return ResponseEntity.status(exception.code).body(ApiErrorDto(exception.message))
    }
}
