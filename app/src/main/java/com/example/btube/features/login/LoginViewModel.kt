package com.example.btube.features.login

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btube.core.correspondence.Event
import com.example.btube.core.correspondence.EventBus
import com.example.btube.data.repository.BiliLoginRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 登录 ViewModel
 * 支持二维码登录、短信登录
 */
class LoginViewModel(
    private val biliLoginRepository: BiliLoginRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _uiState.value
    )
    private var qrcodeLoginJob: Job? = null

    fun onPhoneNumberChange(value: String) {
        _uiState.update { it.copy(phoneNumber = value) }
    }

    fun onCodeChange(value: String) {
        _uiState.update { it.copy(code = value) }
    }

    /** 请求二维码并开始轮询扫描状态 */
    fun requestQrcode() {
        qrcodeLoginJob?.cancel()
        qrcodeLoginJob = viewModelScope.launch {
            runCatching {
                val qrcode = biliLoginRepository.requestQRCode()
                _uiState.update { it.copy(qrCode = qrcode) }
                biliLoginRepository.checkScanStatus(qrcodeKey = qrcode.qrcodeKey) { status ->
                    if (status.code != 0) {
                        EventBus.send(Event.AppEvent.ToastTextEvent(status.message))
                    }
                }
            }.onFailure {
                viewModelScope.launch {
                    EventBus.send(Event.AppEvent.ToastTextEvent("获取二维码失败，请重试"))
                }
            }
        }
    }

    fun onQrcodeLoginCancel() {
        qrcodeLoginJob?.cancel()
        _uiState.update { it.copy(qrCode = null) }
    }

    /** 校验手机号格式（中国大陆 11 位） */
    fun validatedPhoneNumber(): Boolean {
        val phone = _uiState.value.phoneNumber
        val validated = phone.length == 11 && phone.all { it.isDigit() } && phone.startsWith("1")
        _uiState.update { it.copy(isPhoneNumberError = !validated) }
        return validated
    }

    /** 获取验证码（需极验验证码，此处为占位） */
    fun onSendCode() {
        if (!validatedPhoneNumber()) return
        viewModelScope.launch {
            EventBus.send(Event.AppEvent.ToastTextEvent("验证码功能开发中，请使用扫码登录"))
        }
    }

    /** 短信登录（需先完成验证码，此处为占位） */
    fun onSmsLogin() {
        val code = _uiState.value.code
        val validatedCode = code.isNotBlank() && code.isDigitsOnly() && code.length == 6
        if (!validatedCode) {
            _uiState.update { it.copy(isCodeError = true) }
            return
        }
        _uiState.update { it.copy(isCodeError = false) }
        viewModelScope.launch {
            EventBus.send(Event.AppEvent.ToastTextEvent("短信登录需配合验证码，敬请期待"))
        }
    }
}
