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
        viewModelScope.launch {
            try {
                val authService = ApiClient.instance.create(AuthService::class.java)
                val token = ApiClient.tokenBrearer ?: return@launch
                println("Mengirim token: $token") // Debug log
                val response = authService.getProfile(token)
                println("Respons API: ${response.body()}") // Debug log
                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    println("Data profil: ${profileResponse?.data}") // Debug log
                    _profile.value = profileResponse?.data?.firstOrNull()
                } else {
                    println("Gagal mengambil profil: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
}
