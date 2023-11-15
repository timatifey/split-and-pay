package com.example.splitandpay.room.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.room.RoomEvent
import com.example.splitandpay.room.RoomState
import com.example.splitandpay.room.models.ReceiptItem
import com.example.splitandpay.uikit.error.ErrorView
import com.example.splitandpay.uikit.loading.Loading
import com.example.splitandpay.uikit.theme.MyApplicationTheme

@Composable
internal fun RoomView(
    state: RoomState,
    onRoomEvent: OnRoomEvent,
) {
    when (state) {
        is RoomState.Content -> Content(
            state = state,
            onRoomEvent = onRoomEvent,
        )
        is RoomState.Error -> ErrorView(
            text = state.text,
            onClick = { onRoomEvent(RoomEvent.onRetryClick) },
        )
        RoomState.Loading -> Loading()
    }
}

@Composable
private fun Content(
    state: RoomState.Content,
    onRoomEvent: OnRoomEvent,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 100.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(state.items) {
            ReceiptItemView(
                state = it,
                onClick = { onRoomEvent(RoomEvent.onItemClick) }
            )
        }
    }

}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RoomViewPreview() {
    MyApplicationTheme {
        RoomView(
            state = RoomState.Content(
                items = listOf(
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
            ),
            onRoomEvent = {},
        )
    }
}