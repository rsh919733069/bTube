package com.example.btube.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btube.features.login.nav.LoginRoute
import org.koin.androidx.compose.koinViewModel

/**
 * 登录导航容器
 * 支持二维码登录、短信登录切换
 */
@Composable
fun LoginNav(
    onNavigateToMain: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute.QRCode,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        composable<LoginRoute.QRCode> {
            QRCodeLoginScreen(
                uiState = uiState,
                requestQrcode = viewModel::requestQrcode,
                onCancel = viewModel::onQrcodeLoginCancel,
                upPress = onNavigateToMain,
                onSwitchToSms = {
                    navController.navigate(LoginRoute.SMS) {
                        popUpTo(LoginRoute.QRCode) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<LoginRoute.SMS> {
            SMSLoginScreen(
                uiState = uiState,
                onPhoneChange = viewModel::onPhoneNumberChange,
                onCodeChange = viewModel::onCodeChange,
                onCodeSend = viewModel::onSendCode,
                onLogin = viewModel::onSmsLogin,
                onSkip = onNavigateToMain,
                navigateToLoginRoute = { route ->
                    when (route) {
                        LoginRoute.QRCode -> navController.navigate(LoginRoute.QRCode) {
                            popUpTo(LoginRoute.SMS) { inclusive = true }
                        }
                        else -> {}
                    }
                }
            )
        }
    }

    // 顶部提供切换入口：在 QRCode 页可切换到 SMS，在 SMS 页可切换回 QRCode
    // 当前 QRCode 页的 upPress 直接进入主页（跳过登录），SMS 页有切换按钮
}
