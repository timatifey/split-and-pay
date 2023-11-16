package com.example.splitandpay.rooms.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.splitandpay.rooms.add.view.AddRoomView
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddRoomFragment : BottomSheetDialogFragment() {

    private val viewModel: AddRoomViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.shouldDismiss.observe(this) {
            if (it) dismiss()
        }
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    AddRoomView(
                        state = state,
                        onAddRoomEvent = viewModel::onAddRoomEvent,
                    )
                }
            }
        }
    }
}
