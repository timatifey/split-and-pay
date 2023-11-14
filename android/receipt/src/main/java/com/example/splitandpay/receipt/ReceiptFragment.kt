package com.example.splitandpay.receipt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiptFragment : Fragment() {

    private val viewModel: ReceiptViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    val state = viewModel.state.collectAsStateWithLifecycle()
                    Text(text = "Hello world.")
                }
            }
        }
    }
}