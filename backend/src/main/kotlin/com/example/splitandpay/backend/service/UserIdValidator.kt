package com.example.splitandpay.backend.service

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.model.dto.ApiErrorDto
import com.example.splitandpay.backend.repository.UserRepository
import com.example.splitandpay.backend.utils.toObjectId
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import org.apache.catalina.connector.RequestFacade
import org.apache.catalina.connector.ResponseFacade
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

@Component
class UserIdValidator(
    private val userRepository: UserRepository
) : GenericFilterBean() {
    @OptIn(ExperimentalSerializationApi::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        (request as RequestFacade)
        if (request.servletPath.endsWith("/api/user/") && request.method == "POST"
            || request.servletPath.endsWith("/api/misc/randomName")
        ) {
            return chain.doFilter(request, response) // create user request
        }
        val userId = request.getHeader("userid") ?: ""
        try {
            check(userRepository.findById(userId.toObjectId()).isPresent)
        } catch (e: IllegalArgumentException) {
            val error = ApiError.InvalidUserId("null")
            val errorDto = ApiErrorDto(error.message)
            (response as ResponseFacade).status = error.code.value()
            Json.encodeToStream(errorDto, response.outputStream)
            return
        } catch (e: IllegalStateException) {
            val error = ApiError.UserNotFound(userId)
            val errorDto = ApiErrorDto(error.message)
            (response as ResponseFacade).status = error.code.value()
            Json.encodeToStream(errorDto, response.outputStream)
            return
        }
        chain.doFilter(request, response)
    }
}