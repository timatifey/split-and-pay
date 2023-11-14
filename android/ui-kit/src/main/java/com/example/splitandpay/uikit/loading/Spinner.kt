package com.example.splitandpay.uikit.loading

import android.content.res.Configuration
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.uikit.theme.MyApplicationTheme

@Composable
internal fun Spinner() {
    CircularProgressIndicator(
        modifier = Modifier.requiredSize(40.dp),
    )
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SpinnerPreview() {
    MyApplicationTheme {
        Spinner()
    }
}