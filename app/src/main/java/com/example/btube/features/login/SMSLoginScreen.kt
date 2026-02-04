package com.example.btube.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.btube.features.login.nav.LoginRoute

/**
 * 短信验证码登录页（占位）
 * 完整验证码流程需集成极验，后续完善
 */
@Composable
fun SMSLoginScreen(
    uiState: LoginUIState,
    onPhoneChange: (String) -> Unit,
    onCodeChange: (String) -> Unit,
    onCodeSend: () -> Unit,
    onLogin: () -> Unit,
    onSkip: () -> Unit = {},
    navigateToLoginRoute: (LoginRoute) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "短信登录",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.phoneNumber,
            onValueChange = onPhoneChange,
            label = { Text("手机号") },
            placeholder = { Text("请输入手机号") },
            isError = uiState.isPhoneNumberError,
            supportingText = if (uiState.isPhoneNumberError) {
                { Text("请输入正确的手机号") }
            } else null,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.code,
            onValueChange = onCodeChange,
            label = { Text("验证码") },
            placeholder = { Text("请输入6位验证码") },
            isError = uiState.isCodeError,
            supportingText = if (uiState.isCodeError) {
                { Text("请输入6位数字验证码") }
            } else null,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onCodeSend,
            modifier = Modifier.fillMaxSize(0.5f)
        ) {
            Text("获取验证码")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onLogin,
            modifier = Modifier.fillMaxSize(0.5f)
        ) {
            Text("登录")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navigateToLoginRoute(LoginRoute.QRCode) },
            modifier = Modifier.fillMaxSize(0.5f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.QrCode2, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("切换扫码登录")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSkip,
            modifier = Modifier.fillMaxSize(0.5f)
        ) {
            Text("跳过登录")
        }
    }
}
