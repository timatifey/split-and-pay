package com.example.splitandpay.room.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitandpay.room.RoomEvent
import com.example.splitandpay.room.RoomState
import com.example.splitandpay.room.models.ReceiptItem
import com.example.splitandpay.uikit.R
import com.example.splitandpay.uikit.error.ErrorView
import com.example.splitandpay.uikit.loading.Loading
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
internal fun RoomView(
    state: RoomState,
    onRoomEvent: OnRoomEvent,
    onCreateNewProduct: () -> Unit
) {
    when (state) {
        is RoomState.Content -> Content(
            state = state,
            onRoomEvent = onRoomEvent,
        )

        is RoomState.Error -> ErrorView(
            text = state.text,
            onClick = { onRoomEvent(RoomEvent.OnRetryClick) },
        )

        RoomState.Loading -> Loading()

        RoomState.CreateNewProduct -> {
            ErrorView(
                text = "",
                onClick = { onRoomEvent(RoomEvent.OnRetryClick) },
            )
            onCreateNewProduct()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    state: RoomState.Content,
    onRoomEvent: OnRoomEvent,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = false,
        onRefresh = { onRoomEvent(RoomEvent.OnRetryClick) }
    )
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .pullRefresh(pullRefreshState),
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
                ReceiptItemView(
                    state = it,
                    onClick = { onRoomEvent(RoomEvent.OnItemClick(it)) }
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .height(110.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    text = "Итого:",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                )

                Text(
                    textAlign = TextAlign.Start,
                    text = "${state.totalSum}₽",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                )
            }

            Button(
                modifier = Modifier
                    .height(74.dp)
                    .width(74.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(100),
                onClick = {
                    val options = GmsBarcodeScannerOptions.Builder()
                        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                        .enableAutoZoom()
                        .build()
                    val scanner = GmsBarcodeScanning.getClient(context, options)
                    scanner.startScan()
                        .addOnSuccessListener { barcode: Barcode? ->
                            barcode?.rawValue?.let {
                                onRoomEvent(RoomEvent.OnQrCodeScan(it))
                            }
                        }
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_qr_code_scan),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(32.dp),
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                modifier = Modifier
                    .height(74.dp)
                    .width(74.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(100),
                onClick = { onRoomEvent(RoomEvent.CreateReceiptItem) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(32.dp),
                )
            }
        }

        PullRefreshIndicator(
            refreshing = false,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
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
                        mainUser = null,
                        users = emptyList(),
                    ),
                    ReceiptItem(
                        text = "Хлеб",
                        amount = 1.0,
                        mainUser = null,
                        users = emptyList(),
                    ),
                    ReceiptItem(
                        text = "Пиво",
                        amount = 1.0,
                        mainUser = null,
                        users = emptyList(),
                    ),
                ),
                totalSum = 3.0,
            ),
            onRoomEvent = {},
            onCreateNewProduct = {}
        )
    }
}