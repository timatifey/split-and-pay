package com.example.splitandpay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.splitandpay.uikit.loading.Loading
import com.example.splitandpay.uikit.theme.MyApplicationTheme
import com.example.splitandpay.user.UserDataHolder
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private lateinit var navController: NavController
    private val userDataHolder: UserDataHolder by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    Loading()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        val userId = userDataHolder.userId
        val isUserIdExist = userId != null
        if (isUserIdExist) {
            navigateToRoomScreen()
        } else {
            navigateToStartUserScreen()
        }
    }

    private fun navigateToRoomScreen() {
        navController.navigate(R.id.action_mainFragment_to_roomFragment)
    }

    private fun navigateToStartUserScreen() {
        navController.navigate(R.id.action_mainFragment_to_startUserFragment)
    }
}