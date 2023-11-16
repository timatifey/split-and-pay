package com.example.splitandpay.createproduct.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.createproduct.CreateProductEvent
import com.example.splitandpay.createproduct.CreateProductState
import com.example.splitandpay.createproduct.models.NewProduct
import com.example.splitandpay.uikit.theme.MyApplicationTheme

@Composable
internal fun StartUserView(
    state: CreateProductState,
    onCreateProductEvent: (CreateProductEvent) -> Unit,
) {
    when (state) {
        is CreateProductState.Input -> InputContent(
            state = state,
            onCreateProductEvent = onCreateProductEvent,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputContent(
    state: CreateProductState.Input,
    onCreateProductEvent: (CreateProductEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.newProduct.productName,
            onValueChange = { onCreateProductEvent(CreateProductEvent.OnProductNameFieldChange(it)) },
            label = { Text("Product name") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.newProduct.amount.toString(),
            onValueChange = { onCreateProductEvent(CreateProductEvent.OnProductAmountFieldChange(it.toDoubleOrNull())) },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onCreateProductEvent(CreateProductEvent.OnSubmitClick(state.newProduct))
            }
        ) {
            Text(text = "Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StartUserViewPreview() {
    MyApplicationTheme {
        StartUserView(
            state = CreateProductState.Input(
                newProduct = NewProduct(
                    productName = "Пиво",
                    amount = 1.0,
                )
            ),
            onCreateProductEvent = {},
        )
    }
}