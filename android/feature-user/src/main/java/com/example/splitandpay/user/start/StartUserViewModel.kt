package com.example.splitandpay.user.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitandpay.network.SplitAndPayApiService
import com.example.splitandpay.network.model.Username
import com.example.splitandpay.user.UserDataHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class StartUserViewModel constructor(
    private val userDataHolder: UserDataHolder,
    private val apiService: SplitAndPayApiService,
) : ViewModel() {

    private val _state: MutableStateFlow<StartUserState> =
        MutableStateFlow(StartUserState.Input(""))
    val state: StateFlow<StartUserState>
        get() = _state

    fun onStartUserEvent(userEvent: StartUserEvent) {
        when (userEvent) {
            is StartUserEvent.OnSubmitClick -> submitUsername(userEvent.username)
            is StartUserEvent.OnGenerateNameClick -> generateRandomName()
            is StartUserEvent.OnRetry -> {
                _state.value = StartUserState.Input("")
            }
            is StartUserEvent.OnNameFieldChange -> {
                _state.value = StartUserState.Input(userEvent.value)
            }
        }
    }

    private fun submitUsername(username: String) {
        viewModelScope.launch {
            _state.value = StartUserState.Loading
            val createUserResponse = apiService.createUser(Username(username))
            if (!createUserResponse.isSuccessful) {
                setError(createUserResponse.message())
                return@launch
            }

            userDataHolder.userId = createUserResponse.body()!!.id
            userDataHolder.username = username
            _state.value = StartUserState.Success
        }
    }

    private fun generateRandomName() {
        viewModelScope.launch {
            _state.value = StartUserState.Loading
            val randomNameResponse = apiService.getRandomName()
            if (!randomNameResponse.isSuccessful) {
                setError(randomNameResponse.message())
                return@launch
            }

            val randomName = randomNameResponse.body()?.username ?: ""
            _state.value = StartUserState.Input(randomName)
        }
    }

    private fun setError(text: String) {
        _state.value = StartUserState.Error(text)
    }
}
