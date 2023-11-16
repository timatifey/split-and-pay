package com.example.splitandpay.rooms.add.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.rooms.add.AddRoomEvent
import com.example.splitandpay.rooms.add.AddRoomState
import com.example.splitandpay.uikit.theme.MyApplicationTheme

@Composable
internal fun AddRoomView(
    state: AddRoomState,
    onAddRoomEvent: (AddRoomEvent) -> Unit,
    dismiss: () -> Unit,
) {
    when (state) {
        is AddRoomState.Input -> InputContent(
            state = state,
            onAddRoomEvent = onAddRoomEvent,
            dismiss = dismiss,
        )
    }
}

@Composable
private fun InputContent(
    state: AddRoomState.Input,
    onAddRoomEvent: (AddRoomEvent) -> Unit,
    dismiss: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.roomName,
            onValueChange = { onAddRoomEvent(AddRoomEvent.OnRoomNameFieldChange(it)) },
            label = { Text("Room name") },
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            enabled = state.roomName.isNotEmpty(),
            onClick = {
                onAddRoomEvent(AddRoomEvent.OnCreateRoomClick)
                dismiss()
            }
        ) {
            Text(text = "Create")
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.roomId.toString(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
            onValueChange = { onAddRoomEvent(AddRoomEvent.OnRoomIdFieldChange(it)) },
            label = { Text("Room Id") },
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onAddRoomEvent(AddRoomEvent.OnConnectRoomClick)
                dismiss()
            }
        ) {
            Text(text = "Connect")
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StartUserViewPreview() {
    MyApplicationTheme {
        AddRoomView(
            state = AddRoomState.Input(
                roomName = "Party 12.12",
                roomId = 1235334,
            ),
            onAddRoomEvent = {},
            dismiss = {},
        )
    }
}
