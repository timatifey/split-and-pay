package com.example.splitandpay.backend.utils

import com.example.splitandpay.backend.model.db.User
import com.example.splitandpay.backend.model.dto.UserDto
import org.bson.types.ObjectId
import java.util.Locale


@Throws(IllegalArgumentException::class)
fun String.toObjectId() = ObjectId(this)

fun User.toDto(): UserDto = UserDto(id)

private const val WORD_SEPARATOR = " "

fun convertToTitleCaseSplitting(text: String): String {
    return if (text.isEmpty()) {
        text
    } else text.split(WORD_SEPARATOR.toRegex()).dropLastWhile {
        it.isEmpty()
    }.joinToString(WORD_SEPARATOR) { word: String ->
        if (word.isEmpty()) word else word[0].titlecaseChar().toString() + word
            .substring(1)
            .lowercase(Locale.getDefault())
    }
}

fun String.getShortName(): String {
    return convertToTitleCaseSplitting(this)
        .split(" ")
        .take(2).joinToString("") {
            it.first().toString()
        }
}
