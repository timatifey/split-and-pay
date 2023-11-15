package com.example.splitandpay.user.start

internal sealed interface StartUserEvent {

    data class OnSubmitClick(
        val username: String
    ) : StartUserEvent

    data class OnNameFieldChange(
        val value: String
    ) : StartUserEvent

    object OnGenerateNameClick : StartUserEvent

    object OnRetry : StartUserEvent
}
