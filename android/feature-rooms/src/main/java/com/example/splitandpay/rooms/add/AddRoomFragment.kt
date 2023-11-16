package com.example.splitandpay.rooms.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.example.splitandpay.rooms.add.view.AddRoomView
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddRoomFragment : BottomSheetDialogFragment() {

    private lateinit var navController: NavController
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
                        dismiss = ::navigateToRoomsList,
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
    }

    private fun navigateToRoomsList() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://splitandpay/roomsListFragment".toUri())
            .build()
        navController.navigate(request)
    }
}
