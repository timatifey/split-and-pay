package com.example.splitandpay.rooms

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
import com.example.splitandpay.rooms.view.RoomsListView
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomsListFragment : Fragment() {

    private lateinit var navController: NavController
    private val viewModel: RoomsListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    RoomsListView(
                        state = state,
                        username = viewModel.username,
                        onRoomsListEvent = viewModel::onRoomsListEvent,
                        onAddRoomClick = ::navigateToAddRoomScreen,
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
    }

    private fun navigateToAddRoomScreen() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://splitandpay/addRoomFragment".toUri())
            .build()
        navController.navigate(request)
    }
}
