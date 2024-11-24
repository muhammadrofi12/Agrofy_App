package com.example.agrofy_app.viewmodels.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.user.ApiClient
import com.example.agrofy_app.data.api.user.AuthService
import com.example.agrofy_app.data.api.user.UserPreferences
import com.example.agrofy_app.models.user.LoginRequest
import com.example.agrofy_app.models.user.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Initial : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val data: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)
    private val authService = ApiClient.instance.create(AuthService::class.java)

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginUiState.Loading
            try {
                // Menggunakan suspend function untuk login
                val response = authService.login(LoginRequest(email, password))

                if (response.isSuccessful && response.body() != null) {
                    val token = response.body()!!.token

                    Log.d("LoginViewModel", "Token: $token")
                    // Menyimpan token ke UserPreferences
                    userPreferences.saveToken(token)
                    // Menyimpan token ke RetrofitClient
                    ApiClient.setToken(token)
                    // Update status menjadi Success dengan LoginResponse
                    _loginState.value = LoginUiState.Success(response.body()!!)
                } else {
                    // Jika login gagal
                    _loginState.value = LoginUiState.Error("Login gagal: ${response.message()}")
                }
            } catch (e: Exception) {
                // Menangani error lainnya
                _loginState.value = LoginUiState.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.clearUser()
        }
    }
}
