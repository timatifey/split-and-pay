package com.example.splitandpay.backend.utils

import java.util.UUID

@Throws(IllegalArgumentException::class)
fun String.toUUID(): UUID = UUID.fromString(this)
