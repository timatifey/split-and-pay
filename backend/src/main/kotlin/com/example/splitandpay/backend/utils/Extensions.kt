package com.example.splitandpay.backend.utils

import com.example.splitandpay.backend.model.db.User
import com.example.splitandpay.backend.model.dto.UserDto
import java.util.UUID

@Throws(IllegalArgumentException::class)
fun String.toUUID(): UUID = UUID.fromString(this)

fun User.toDto(): UserDto = UserDto(id)