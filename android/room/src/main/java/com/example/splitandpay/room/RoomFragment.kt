package com.example.splitandpay.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.example.splitandpay.room.view.RoomView
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomFragment : Fragment() {

    private lateinit var navController: NavController
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
                        onCreateNewProduct = ::navigateToCreateProductScreen
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
    }

    private fun navigateToCreateProductScreen() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://splitandpay/createProductFragment".toUri())
            .build()
        navController.navigate(request)
    }
}