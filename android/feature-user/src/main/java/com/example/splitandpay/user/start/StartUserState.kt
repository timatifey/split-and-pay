package com.example.splitandpay.user.start

internal sealed interface StartUserState {

    data class Input(
        val username: String,
    ) : StartUserState

    data class Error(
        val text: String,
    ) : StartUserState

    object Loading : StartUserState

    object Success : StartUserState
}
