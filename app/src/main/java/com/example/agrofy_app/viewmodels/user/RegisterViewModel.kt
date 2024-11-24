package com.example.agrofy_app.viewmodels.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.user.ApiClient
import com.example.agrofy_app.data.api.user.AuthService
import com.example.agrofy_app.models.user.RegisterRequest
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject


sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}

class RegisterViewModel : ViewModel() {
    private val TAG = "RegisterViewModel"
    private val authService = ApiClient.instance.create(AuthService::class.java)
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        if (validateInput(name, email, password, confirmPassword)) {
            viewModelScope.launch {
                try {
                    _registerState.value = RegisterState.Loading
                    Log.d(TAG, "Attempting registration for email: $email")

                    val registerRequest = RegisterRequest(
                        namaLengkap = name,  // Changed from name to namaLengkap
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword,
                        foto = null
                    )

                    Log.d(TAG, "Register request: ${Gson().toJson(registerRequest)}")

                    val response = authService.register(registerRequest)
                    Log.d(TAG, "Registration response code: ${response.code()}")

                    if (response.isSuccessful) {
                        Log.d(TAG, "Registration successful")
                        _registerState.value = RegisterState.Success
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e(TAG, "Registration failed. Error body: $errorBody")

                        val errorMessage = try {
                            JSONObject(errorBody ?: "").getString("msg")
                        } catch (e: Exception) {
                            errorBody ?: "Registration failed: ${response.message()}"
                        }

                        _registerState.value = RegisterState.Error(errorMessage)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Registration exception", e)
                    _registerState.value = RegisterState.Error("Registration failed: ${e.message}")
                }
            }
        }
    }

    private fun validateInput(name: String, email: String, password: String, confirmPassword: String): Boolean {
        if (name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _registerState.value = RegisterState.Error("Semua field harus diisi")
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _registerState.value = RegisterState.Error("Format email tidak valid")
            return false
        }

        if (password.length < 6) {
            _registerState.value = RegisterState.Error("Password minimal 6 karakter")
            return false
        }

        if (password != confirmPassword) {
            _registerState.value = RegisterState.Error("Password tidak cocok")
            return false
        }

        return true
    }

    fun resetState() {
        _registerState.value = RegisterState.Idle
    }
}