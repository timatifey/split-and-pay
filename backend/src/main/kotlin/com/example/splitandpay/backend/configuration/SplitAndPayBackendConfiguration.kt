package com.example.splitandpay.backend.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class SplitAndPayBackendConfiguration {
    @Bean
    fun webClient(webClientBuilder: WebClient.Builder): WebClient {
        return webClientBuilder.build()
    }
}
