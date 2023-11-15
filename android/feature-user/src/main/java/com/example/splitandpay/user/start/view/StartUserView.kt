package com.example.splitandpay.user.start.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.splitandpay.uikit.error.ErrorView
import com.example.splitandpay.uikit.loading.Loading
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.example.splitandpay.user.start.StartUserEvent
import com.example.splitandpay.user.start.StartUserState

@Composable
internal fun StartUserView(
    state: StartUserState,
    onStartUserEvent: (StartUserEvent) -> Unit,
    onSuccess: () -> Unit
) {
    when (state) {
        is StartUserState.Input -> InputContent(
            state = state,
            onStartUserEvent = onStartUserEvent,
        )

        is StartUserState.Error -> ErrorView(
            text = state.text,
            onClick = {
                onStartUserEvent(StartUserEvent.OnNameFieldChange(""))
            },
        )

        is StartUserState.Loading -> Loading()
        is StartUserState.Success -> onSuccess()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputContent(
    state: StartUserState.Input,
    onStartUserEvent: (StartUserEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = state.username,
            onValueChange = { onStartUserEvent(StartUserEvent.OnNameFieldChange(it)) },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = {
                onStartUserEvent(StartUserEvent.OnGenerateNameClick)
            }) {
                Text(text = "Randomize name")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                onStartUserEvent(StartUserEvent.OnSubmitClick(state.username))
            }) {
                Text(text = "Submit")
            }
        }

    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StartUserViewPreview() {
    MyApplicationTheme {
        StartUserView(
            state = StartUserState.Input(
                username = "Alex",
            ),
            onStartUserEvent = {},
            onSuccess = {},
        )
    }
}
