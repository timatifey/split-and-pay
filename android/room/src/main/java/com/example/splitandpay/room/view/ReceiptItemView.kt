package com.example.splitandpay.room.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.room.models.ReceiptItem
import com.example.splitandpay.room.models.User
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.example.splitandpay.uikit.theme.PurpleGrey40

@Composable
internal fun ReceiptItemView(
    state: ReceiptItem,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(PurpleGrey40)
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            textAlign = TextAlign.Start,
            text = state.text,
            maxLines = 2,
        )
        
        Spacer(modifier = Modifier.weight(1f))

        LazyRow(
            reverseLayout = true,
            modifier = Modifier.width(88.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
        ) {
            items(state.users) {
                UserLogo(
                    text = it.shortName,
                )
            }
        }
    }

}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ReceiptItemPreview() {
    MyApplicationTheme {
        ReceiptItemView(
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
                ),
            ),
            onClick = {},
        )
    }
}