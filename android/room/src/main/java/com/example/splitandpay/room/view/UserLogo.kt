package com.example.splitandpay.room.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.uikit.theme.MyApplicationTheme

@Composable
internal fun UserLogo(
    text: String,
    isMain: Boolean = false,
) {
    val background = if (isMain) Color.Yellow else Color.White
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(1000.dp))
            .background(background)
            .size(40.dp),
        contentAlignment = Alignment.Center,
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
private fun UserLogoPreview() {
    MyApplicationTheme {
        UserLogo(
            text = "АО"
        )
    }
}