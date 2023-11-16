package com.example.splitandpay.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitandpay.network.SplitAndPayApiService
import com.example.splitandpay.room.RoomDataHolder
import com.example.splitandpay.rooms.model.RoomsListItem
import com.example.splitandpay.user.UserDataHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

internal class RoomsListViewModel constructor(
    private val userDataHolder: UserDataHolder,
    private val apiService: SplitAndPayApiService,
) : ViewModel() {

    private val _state: MutableStateFlow<RoomsListState> = MutableStateFlow(RoomsListState.Loading)
    val state: StateFlow<RoomsListState>
        get() = _state

    private val userId: String
        get() = userDataHolder.userId!!

    val username: String
        get() = userDataHolder.username!!

    init {
        fetchRooms()
    }

    private val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    private val uiFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

    fun onRoomsListEvent(event: RoomsListEvent) {
        when (event) {
            is RoomsListEvent.OnRoomsItemClick -> {
                RoomDataHolder.roomId = event.roomId
            }
            is RoomsListEvent.OnAddButtonClick -> {}
            is RoomsListEvent.OnRetryClick -> fetchRooms()
            is RoomsListEvent.OnPullToRefresh -> fetchRooms()
        }
    }

    private fun fetchRooms() {
        viewModelScope.launch {
            _state.value = RoomsListState.Loading
            val response = apiService.getRooms(userId)
            if (!response.isSuccessful) {
                _state.value = RoomsListState.Error(response.message())
                return@launch
            }
            val rooms = response.body()!!
            _state.value = RoomsListState.Content(rooms.map {
                RoomsListItem(
                    id = it.id,
                    name = it.name,
                    createdAt = uiFormat.format(format.parse(it.createdAt)),
                )
            })
        }
    }
}
