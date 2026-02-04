package com.example.btube

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.btube.core.IS_LOGIN_KEY
import com.example.btube.core.correspondence.Event
import com.example.btube.core.correspondence.EventBus
import com.example.btube.data.local.datastore.dataStore
import com.example.btube.nav.AppNav
import com.example.btube.nav.AppRoute
import com.example.btube.ui.theme.BTubeTheme
import com.example.btube.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
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
            val currentDestination by appNavController.currentBackStackEntryAsState()

            AppEventListener()

            ObserveLoginState(updateAppState = sharedViewModel::updateAppState)

            LaunchedEffect(appState.isLogin) {
                if (!appState.isLogin) return@LaunchedEffect
                val isLoginRoute =
                    currentDestination?.destination?.hasRoute(AppRoute.LoginNav::class) == true
                if (isLoginRoute) {
                    appNavController.navigate(AppRoute.MainNav) {
                        popUpTo<AppRoute.LoginNav> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }

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

@Composable
private fun AppEventListener() {
    val activity = LocalContext.current as? ComponentActivity ?: return
    LaunchedEffect(Unit) {
        EventBus.events.collect { event ->
            if (event !is Event.AppEvent) return@collect
            when (event) {
                is Event.AppEvent.ToastTextEvent -> {
                    Toast.makeText(activity, event.message, Toast.LENGTH_SHORT).show()
                }
                is Event.AppEvent.ToastEvent -> {
                    Toast.makeText(activity, activity.getText(event.messageId), Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }
}

@Composable
private fun ObserveLoginState(
    updateAppState: (AppState) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        context.dataStore.data
            .map { it[IS_LOGIN_KEY] == true }
            .distinctUntilChanged()
            .collect { isLogin ->
                updateAppState(AppState(isLogin = isLogin))
            }
    }
}
