package com.example.splitandpay.backend.service.check

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.service.check.dto.CheckDto
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class ProverkaCheckaService(
    private val webClient: WebClient,
    @Value("\${proverka.cheka.token}")
    private val token: String
) {

    fun sendCheck(checkData: String): CheckDto {
        return webClient.post()
            .uri("https://proverkacheka.com/api/v1/check/get")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue("$checkData&token=$token")
            .retrieve()
            .onStatus({ it.isError }) {
                throw ApiError.ServiceUnavailable
            }
            .bodyToMono<String>()
            .blockOptional()
            .orElseThrow { ApiError.ServiceUnavailable }
            .let {
                Json {
                    ignoreUnknownKeys = true
                }.decodeFromString(CheckDto.serializer(), it)
            }
    }
}