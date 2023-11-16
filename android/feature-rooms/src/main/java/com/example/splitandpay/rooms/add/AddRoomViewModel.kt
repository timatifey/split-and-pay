package com.example.splitandpay.rooms.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitandpay.network.SplitAndPayApiService
import com.example.splitandpay.network.model.RoomName
import com.example.splitandpay.user.UserDataHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class AddRoomViewModel(
    private val apiService: SplitAndPayApiService,
    private val userDataHolder: UserDataHolder,
) : ViewModel() {

    private var currentRoomName: String = ""
    private var currentRoomId: Long = 0L

    private val _shouldDismiss = MutableLiveData(false)
    val shouldDismiss: LiveData<Boolean>
        get() = _shouldDismiss

    private val _state: MutableStateFlow<AddRoomState> = MutableStateFlow(
        AddRoomState.Input(
            roomName = currentRoomName,
            roomId = currentRoomId,
        )
    )
    val state: StateFlow<AddRoomState>
        get() = _state

    private val userId: String
        get() = userDataHolder.userId!!

    fun onAddRoomEvent(addRoomEvent: AddRoomEvent) {
        when (addRoomEvent) {
            is AddRoomEvent.OnCreateRoomClick -> createRoom(currentRoomName)
            is AddRoomEvent.OnConnectRoomClick -> connectToRoom(currentRoomId)
            is AddRoomEvent.OnRoomIdFieldChange -> changeRoomId(addRoomEvent.value)
            is AddRoomEvent.OnRoomNameFieldChange -> changeRoomName(addRoomEvent.value)
        }
    }

    private fun changeRoomId(roomId: String) {
        try {
            currentRoomId = roomId.toLong()
            setInputStateForUi()
        } catch (ignored: NumberFormatException) {
        }
    }

    private fun changeRoomName(roomName: String) {
        currentRoomName = roomName
        setInputStateForUi()
    }

    private fun setInputStateForUi() {
        _state.value = AddRoomState.Input(
            roomName = currentRoomName,
            roomId = currentRoomId,
        )
    }

    private fun createRoom(name: String) {
        viewModelScope.launch {
            apiService.createRoom(
                userId = userId,
                roomName = RoomName(
                    roomName = name,
                ),
            )
        }

        _shouldDismiss.postValue(true)
    }

    private fun connectToRoom(roomId: Long) {
        viewModelScope.launch {
            apiService.connectToRoom(
                userId = userId,
                roomId = roomId,
            )
        }

        _shouldDismiss.postValue(true)
    }
}
