package com.example.agrofy_app.viewmodels.user

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.user.ApiClient
import com.example.agrofy_app.data.api.user.AuthService
import com.example.agrofy_app.models.user.ProfileRequest
import com.example.agrofy_app.models.user.UserProfile
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileViewModel : ViewModel() {
    private val gson = Gson()

    private val _profile = MutableStateFlow<UserProfile?>(null)
    val profile: StateFlow<UserProfile?> = _profile

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadProfile() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val authService = ApiClient.instance.create(AuthService::class.java)
                val token = ApiClient.tokenBrearer ?: run {
                    println("Token tidak tersedia")
                    _isLoading.value = false
                    return@launch
                }

                val response = authService.getProfile(token)
                _isLoading.value = false

                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    val dataElement = profileResponse?.data

                    // Handling jika `data` berupa array atau objek
                    if (dataElement != null) {
                        when {
                            dataElement.isJsonObject -> {
                                _profile.value = gson.fromJson(dataElement, UserProfile::class.java)
                            }

                            dataElement.isJsonArray -> {
                                val firstItem = dataElement.asJsonArray.firstOrNull()
                                _profile.value = firstItem?.let {
                                    gson.fromJson(it, UserProfile::class.java)
                                }
                            }

                            else -> {
                                println("Data format tidak dikenal: $dataElement")
                            }
                        }
                    }
                } else {
                    println("Gagal mengambil profil: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                println("Error mengambil profil: ${e.message}")
            }
        }
    }

    // Edit Profile
    fun editProfile(
        profileRequest: ProfileRequest,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val authService = ApiClient.instance.create(AuthService::class.java)
                val token = ApiClient.tokenBrearer ?: return@launch onError("Token tidak tersedia")

                val requestMap = mapOf(
                    "nama_lengkap" to profileRequest.namaLengkap,
                    "email" to profileRequest.email
                )

                val response = authService.editProfile(token, requestMap)
                _isLoading.value = false

                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    val dataElement = profileResponse?.data

                    // Handling jika `data` berupa array atau objek
                    if (dataElement != null) {
                        when {
                            dataElement.isJsonObject -> {
                                _profile.value = gson.fromJson(dataElement, UserProfile::class.java)
                            }

                            dataElement.isJsonArray -> {
                                val firstItem = dataElement.asJsonArray.firstOrNull()
                                _profile.value = firstItem?.let {
                                    gson.fromJson(it, UserProfile::class.java)
                                }
                            }
                        }
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

    // Edit Foto
    fun uploadPhoto(photoUri: Uri, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                val authService = ApiClient.instance.create(AuthService::class.java)
                val token = ApiClient.tokenBrearer ?: return@launch onComplete(
                    false,
                    "Token tidak tersedia"
                )

                val file = File(photoUri.path!!)
                val requestBody = file
                    .asRequestBody("image/jpeg".toMediaTypeOrNull())
                val multipartBody =
                    MultipartBody.Part.createFormData("foto", file.name, requestBody)

                val response = authService.uploadProfileImage(token, multipartBody)
                if (response.isSuccessful) {
                    loadProfile()
                    onComplete(true, null)
                } else {
                    onComplete(false, response.errorBody()?.string())
                }
            } catch (e: Exception) {
                onComplete(false, e.message)
            }
        }
    }

    // Edit password
    fun editPassword(
        password: String,
        confirmPassword: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val authService = ApiClient.instance.create(AuthService::class.java)
                val token = ApiClient.tokenBrearer ?: return@launch onError("Token tidak tersedia")

                val requestMap = mapOf(
                    "newPassword" to password,
                    "confirmPassword" to confirmPassword
                )


                // Log data yang akan dikirim
                Log.d("EditPassword", "Request Map: $requestMap")

                val response = authService.editPassword(token, requestMap)

                // Log status response
                Log.d("EditPassword", "Response Code: ${response.code()}")
                Log.d("EditPassword", "Response Body: ${response.body()}")
                Log.d("EditPassword", "Response Error Body: ${response.errorBody()?.string()}")

                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError(response.errorBody()?.string() ?: "Kesalahan tidak diketahui")
                }
            } catch (e: Exception) {
                // Log jika terjadi kesalahan
                Log.e("EditPassword", "Exception: ${e.message}", e)
                onError(e.message ?: "Terjadi kesalahan")
            }
        }
    }


}
