package com.legal.aichatbot.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legal.aichatbot.data.model.User
import com.legal.aichatbot.data.repository.AuthRepository
import com.legal.aichatbot.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _authState = MutableStateFlow<NetworkResult<User>?>(null)
    val authState: StateFlow<NetworkResult<User>?> = _authState

    private val _isLoggedIn = MutableStateFlow(authRepository.currentUser != null)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = NetworkResult.Loading()
            val result = authRepository.login(email, password)
            _authState.value = result
            if (result is NetworkResult.Success) {
                _isLoggedIn.value = true
            }
        }
    }

    fun register(email: String, password: String, displayName: String) {
        viewModelScope.launch {
            _authState.value = NetworkResult.Loading()
            val result = authRepository.register(email, password, displayName)
            _authState.value = result
            if (result is NetworkResult.Success) {
                _isLoggedIn.value = true
            }
        }
    }

    fun logout() {
        authRepository.logout()
        _isLoggedIn.value = false
        _authState.value = null
    }

    fun resetAuthState() {
        _authState.value = null
    }
}