package com.example.agrofy_app.viewmodels.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.user.ApiClient
import com.example.agrofy_app.data.api.user.AuthService
import com.example.agrofy_app.models.user.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profile = MutableStateFlow<UserProfile?>(null)
    val profile: StateFlow<UserProfile?> = _profile

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadProfile() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val authService = ApiClient.instance.create(AuthService::class.java)
                val token = ApiClient.tokenBrearer
                    ?: run {
                        println("Token tidak tersedia")
                        _isLoading.value = false
                        return@launch
                    }

                val response = authService.getProfile(token)
                _isLoading.value = false

                when {
                    response.isSuccessful -> {
                        val profileResponse = response.body()
                        _profile.value = profileResponse?.data
                        println("Profil berhasil dimuat: ${profileResponse?.data}")
                    }
                    response.code() == 401 -> {
                        println("Unauthorized: Token mungkin tidak valid")
                    }
                    else -> {
                        println("Gagal mengambil profil: ${response.errorBody()?.string()}")
                    }
                }
            } catch (e: Exception) {
                _isLoading.value = false
                println("Error mengambil profil: ${e.message}")
            }
        }
    }
}
