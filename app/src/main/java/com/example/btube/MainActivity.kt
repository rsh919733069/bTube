package com.example.btube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.btube.nav.AppNav
import com.example.btube.ui.theme.BTubeTheme
import com.example.btube.ui.viewmodel.SharedViewModel
import org.koin.compose.koinInject

/**
 * 主Activity
 * bTube应用的入口Activity
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val sharedViewModel = koinInject<SharedViewModel>()
            val appNavController = rememberNavController()
            val appState by sharedViewModel.appState.collectAsStateWithLifecycle()

            BTubeTheme {
                AppNav(
                    sharedViewModel = sharedViewModel,
                    appNavController = appNavController,
                    appState = appState
                )
            }
        }
    }
}
