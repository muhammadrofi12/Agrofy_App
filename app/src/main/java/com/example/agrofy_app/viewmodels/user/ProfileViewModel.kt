package com.example.agrofy_app.viewmodels.user

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.user.ApiClient
import com.example.agrofy_app.data.api.user.AuthService
import com.example.agrofy_app.models.user.ProfileRequest
import com.example.agrofy_app.models.user.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.io.File

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

    fun editProfile(profileRequest: ProfileRequest, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val authService = ApiClient.instance.create(AuthService::class.java)
                val token = ApiClient.tokenBrearer ?: return@launch onError("Token tidak tersedia")

                val requestMap = mapOf(
                    "nama_lengkap" to profileRequest.namaLengkap,
                    "email" to profileRequest.email,
                )

                val response = authService.editProfile(token, requestMap)
                _isLoading.value = false

                if (response.isSuccessful) {
                    val updatedProfile = response.body()?.data
                    if (updatedProfile != null) {
                        _profile.value = updatedProfile // Update state dengan data baru
                    }
                    onSuccess()
                } else {
                    onError("Gagal memperbarui profil: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                onError("Terjadi kesalahan: ${e.message}")
            }
        }
    }


    fun uploadPhoto(photoUri: Uri, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val authService = ApiClient.instance.create(AuthService::class.java)
                val token = ApiClient.tokenBrearer ?: return@launch onComplete(false, "Token tidak tersedia")

                val requestBody = RequestBody.create(
                    "image/*".toMediaTypeOrNull(),
                    File(photoUri.path!!)
                )

                val response = authService.uploadProfileImage(token, requestBody)
                _isLoading.value = false

                if (response.isSuccessful) {
                    _profile.value = response.body()?.data
                    onComplete(true, null)
                } else {
                    onComplete(false, response.errorBody()?.string())
                }
            } catch (e: Exception) {
                _isLoading.value = false
                onComplete(false, e.message)
            }
        }
    }


}
