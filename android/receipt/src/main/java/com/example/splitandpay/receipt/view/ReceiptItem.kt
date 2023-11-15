package com.example.splitandpay.receipt.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.example.splitandpay.uikit.theme.SpaceGray

@Composable
internal fun ReceiptItem(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(SpaceGray)
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            textAlign = TextAlign.Start,
            text = text,
            maxLines = 1,
        )
    }

}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ReceiptItemPreview() {
    MyApplicationTheme {
        ReceiptItem(
            "Пиво",
            onClick = {},
        )
    }
}