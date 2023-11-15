package com.example.splitandpay.user.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.example.splitandpay.user.start.view.StartUserView
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalMaterial3Api
class StartUserFragment : Fragment() {

    private lateinit var navController: NavController
    private val viewModel: StartUserViewModel by viewModel()

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
                        onStartUserEvent = viewModel::onStartUserEvent,
                        onSuccess = ::navigateToMainScreen,
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
    }

    private fun navigateToMainScreen() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://splitandpay/mainFragment".toUri())
            .build()
        navController.navigate(request)
    }
}
