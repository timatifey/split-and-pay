package com.example.splitandpay.backend.controller

import com.example.splitandpay.backend.exception.ApiError
import com.example.splitandpay.backend.model.dto.RandomNameDto
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import kotlin.random.Random

@RestController
@RequestMapping("/api/misc")
class RandomNameGeneratorController(
    private val webClient: WebClient
) {
    @GetMapping("/randomName")
    fun generateRandomName(): RandomNameDto {
        val formData = LinkedMultiValueMap<String, String>().apply {
            val word = words.random()
            add("type", "generatom")
            add("words", word)
            add("keys", "probel")
            add("register_name", "uc_word")
            if (randomBoolean(42))
                add("word", "")
        }
        return webClient.post()
            .uri("https://generatom.com/ajax")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono<String>()
            .blockOptional().orElseThrow { ApiError.ServiceUnavailable }
            .replace(replaceDashAndUnderline, " ").trim().let {
                RandomNameDto(it)
            }

    }

    companion object {
        private val replaceDashAndUnderline = "[_.\\]\\[{}-]".toRegex()
        private val words = listOf("humor", "hishnik", "fen", "stalker", "dino", "sleng")

        private fun randomBoolean(threshold: Int) = Random.nextInt(0, 100) < threshold
    }
}
