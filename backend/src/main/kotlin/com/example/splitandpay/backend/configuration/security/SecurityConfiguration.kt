package com.example.splitandpay.backend.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean("secureFilter")
    @Throws(Exception::class)
    fun secureFilterChain(httpSecurity: HttpSecurity): DefaultSecurityFilterChain {
        return httpSecurity
            .httpBasic { it.disable() }
            .cors {
                it.configurationSource {
                    CorsConfiguration().apply {
                        applyPermitDefaultValues()
                        allowedMethods = listOf(
                            HttpMethod.GET.name(),
                            HttpMethod.POST.name(),
                            HttpMethod.PUT.name(),
                            HttpMethod.DELETE.name()
                        )
                    }
                }
            }.csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }.authorizeHttpRequests { auth ->
                auth.anyRequest().permitAll()
            }.build()
    }
}