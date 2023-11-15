package com.example.splitandpay.rooms.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.rooms.RoomsListEvent
import com.example.splitandpay.rooms.RoomsListState
import com.example.splitandpay.rooms.model.RoomsListItem
import com.example.splitandpay.uikit.R
import com.example.splitandpay.uikit.error.ErrorView
import com.example.splitandpay.uikit.loading.Loading
import com.example.splitandpay.uikit.theme.MyApplicationTheme

@Composable
internal fun RoomsListView(
    state: RoomsListState,
    username: String,
    onRoomsListEvent: (RoomsListEvent) -> Unit,
) {
    Column {
        UserHeader(username)
        when (state) {
            is RoomsListState.Content -> Content(
                state = state,
                onRoomsListEvent = onRoomsListEvent,
            )

            is RoomsListState.Error -> ErrorView(
                text = state.text,
                onClick = { onRoomsListEvent(RoomsListEvent.OnRetryClick) },
            )

            is RoomsListState.Loading -> Loading()
            is RoomsListState.EmptyList -> EmptyList(onRoomsListEvent = onRoomsListEvent)
        }
    }
}

@Composable
private fun UserHeader(username: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.user_header, username),
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
private fun EmptyList(
    onRoomsListEvent: (RoomsListEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.empty_list_text),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
        Button(
            modifier = Modifier
                .height(64.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(size = 1000.dp),
            onClick = { onRoomsListEvent(RoomsListEvent.OnAddButtonClick) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                modifier = Modifier.requiredSize(28.dp),
            )
        }
    }
}

@Composable
private fun Content(
    state: RoomsListState.Content,
    onRoomsListEvent: (RoomsListEvent) -> Unit,
) {
    Box(
        modifier = Modifier.padding(horizontal = 8.dp),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 120.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.items) {
                RoomsItemView(
                    state = it,
                    onClick = { onRoomsListEvent(RoomsListEvent.OnRoomsItemClick(it.id)) }
                )
            }
        }

        Button(
            modifier = Modifier
                .height(64.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(size = 1000.dp),
            onClick = { onRoomsListEvent(RoomsListEvent.OnAddButtonClick) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                modifier = Modifier.requiredSize(28.dp),
            )
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RoomsListViewPreview() {
    MyApplicationTheme {
        RoomsListView(
            state = RoomsListState.Content(
                items = listOf(
                    RoomsListItem(
                        id = 1,
                        createdAt = "16.11.2023 01:54",
                    ),
                    RoomsListItem(
                        id = 2,
                        createdAt = "16.11.2023 01:54",
                    ),
                    RoomsListItem(
                        id = 3,
                        createdAt = "16.11.2023 01:54",
                    ),
                )
            ),
            username = "Алексей",
            onRoomsListEvent = {},
        )
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RoomsListViewEmptyPreview() {
    MyApplicationTheme {
        RoomsListView(
            state = RoomsListState.EmptyList,
            username = "Алексей",
            onRoomsListEvent = {},
        )
    }
}

