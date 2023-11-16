package com.example.splitandpay.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitandpay.network.SplitAndPayApiService
import com.example.splitandpay.network.model.UserToProduct
import com.example.splitandpay.network.model.RoomDetails
import com.example.splitandpay.room.models.ReceiptItem
import com.example.splitandpay.room.models.User
import com.example.splitandpay.user.UserDataHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class RoomViewModel(
    private val apiService: SplitAndPayApiService,
    private val userDataHolder: UserDataHolder,
    private val roomModelMapper: RoomModelMapper,
) : ViewModel() {

    private val mockState = RoomState.Content(
        items = listOf(
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = listOf(
                    User(
                        id = "",
                        username = "Андрей Остапчук",
                        shortName = "АО",
                    ),
                ),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = listOf(
                    User(
                        id = "",
                        username = "Тимофей Плетнёв",
                        shortName = "ТП",
                    ),
                ),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = listOf(
                    User(
                        id = "",
                        username = "Андрей Остапчук",
                        shortName = "АО",
                    ),
                    User(
                        id = "",
                        username = "Тимофей Плетнёв",
                        shortName = "ТП",
                    ),
                    User(
                        id = "",
                        username = "Виктор Коротких",
                        shortName = "ВК",
                    ),
                ),
            ),
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
        )
    )

    private val _state: MutableStateFlow<RoomState> = MutableStateFlow(mockState)
    val state: StateFlow<RoomState>
        get() = _state

    private val userId: String
        get() = userDataHolder.userId!!

    init {
        //fetchRoom()
    }

    fun onReceiptEvent(roomEvent: RoomEvent) {
        when (roomEvent) {
            RoomEvent.OnRetryClick -> onRetryClick()
            is RoomEvent.OnItemClick -> onItemClick(roomEvent.receiptItem)
            RoomEvent.CreateReceiptItem -> onCreateReceiptItemClick()
        }
    }

    private fun onRetryClick() {
        _state.value = RoomState.Loading
        fetchRoom()
    }

    private fun onItemClick(receiptItem: ReceiptItem) {
        viewModelScope.launch {
            val response = apiService.addUserToProduct(
                userId = userId,
                roomId = 1, // replace hardcode with args
                userToProduct = UserToProduct(
                    productId = receiptItem.id,
                    userId = userId,
                )
            )

            if (!response.isSuccessful) {
                setError(response.message())
                return@launch
            }

            setContent(response.body()!!)
        }
    }

    private fun onCreateReceiptItemClick() {
        _state.value = RoomState.CreateNewProduct
    }

    private fun fetchRoom() {
        viewModelScope.launch {
            val response = apiService.getRoomDetails(
                userId = userId,
                roomId = 1, // replace hardcode with args
            )

            if (!response.isSuccessful) {
                setError(response.message())
                return@launch
            }

            setContent(response.body()!!)
        }
    }

    private fun setError(text: String) {
        _state.value = RoomState.Error(text)
    }

    private fun setContent(roomDetails: RoomDetails) {
        _state.value = RoomState.Content(
            items = roomDetails.receipt.map(roomModelMapper::mapReceiptItem)
        )
    }
}