package com.example.splitandpay.createproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.splitandpay.createproduct.view.StartUserView
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateProductFragment : BottomSheetDialogFragment() {

    private val viewModel: CreateProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    StartUserView(
                        state = state,
                        onCreateProductEvent = viewModel::onCreateProductEvent,
                    )
                }
            }
        }
    }
}