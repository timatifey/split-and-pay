package com.example.splitandpay.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.splitandpay.room.view.RoomView
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomFragment : Fragment() {

    private val viewModel: RoomViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    RoomView(
                        state = state,
                        onRoomEvent = viewModel::onReceiptEvent,
                    )
                }
            }
        }
    }
}