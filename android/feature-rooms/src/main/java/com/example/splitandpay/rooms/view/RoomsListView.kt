package com.example.splitandpay.rooms.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun RoomsListView(
    state: RoomsListState,
    username: String,
    onRoomsListEvent: (RoomsListEvent) -> Unit,
    onAddRoomClick: () -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state is RoomsListState.Loading,
        onRefresh = { onRoomsListEvent(RoomsListEvent.OnRetryClick) }
    )
    Scaffold(
        modifier = Modifier.pullRefresh(pullRefreshState),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Add room") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = { onAddRoomClick.invoke() }
            )
        }
    ) { contentPaddings: PaddingValues ->
        Box(
            modifier = Modifier.padding(contentPaddings),
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
                }
            }
            PullRefreshIndicator(
                refreshing = state is RoomsListState.Loading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
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
private fun Content(
    state: RoomsListState.Content,
    onRoomsListEvent: (RoomsListEvent) -> Unit,
) {
    Box(
        modifier = Modifier.padding(horizontal = 8.dp),
    ) {
        if (state.items.isEmpty()) {
            EmptyBox()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
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
        }
    }
}

@Composable
private fun EmptyBox() {
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
                        name = "Party 1",
                        createdAt = "16.11.2023 01:54",
                    ),
                    RoomsListItem(
                        id = 2,
                        name = "Party 2",
                        createdAt = "16.11.2023 01:54",
                    ),
                    RoomsListItem(
                        id = 3,
                        name = "Party 3",
                        createdAt = "16.11.2023 01:54",
                    ),
                )
            ),
            username = "Алексей",
            onRoomsListEvent = {},
            onAddRoomClick = {},
        )
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RoomsListViewEmptyPreview() {
    MyApplicationTheme {
        RoomsListView(
            state = RoomsListState.Content(items = listOf()),
            username = "Алексей",
            onRoomsListEvent = {},
            onAddRoomClick = {},
        )
    }
}
