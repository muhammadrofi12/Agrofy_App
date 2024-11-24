package com.example.agrofy_app.viewmodels.pemberdayaan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.pemberdayaan.KategoriRetrofitClient
import com.example.agrofy_app.data.api.user.ApiClient
import com.example.agrofy_app.data.api.user.UserPreferences
import com.example.agrofy_app.models.pemberdayaan.KategoriResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KategoriViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)
    private val _kategori = MutableStateFlow<List<KategoriResponse>>(emptyList())
    val kategori: StateFlow<List<KategoriResponse>> = _kategori.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.getToken.collect { token ->
                ApiClient.setToken(token)
                if (!token.isNullOrEmpty()) {
                    fetchKategori()
                }
                Log.d("KategoriViewModel", "Token set: $token")
            }
        }
    }

    private fun fetchKategori() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val kategoriDeferred = async { KategoriRetrofitClient.instance.getKategori() }
                val response = kategoriDeferred.await()

                // Log API Response
                Log.d("KategoriViewModel", "API Response: ${response.code()} - ${response.message()}")

                // Handle Response
                if (response.isSuccessful) {
                    response.body()?.let { apiResponse ->
                        Log.d("KategoriViewModel", "Kategori API Response: $apiResponse")
                        _kategori.value = apiResponse.data
                    } ?: run {
                        _error.value = "Response body is empty"
                        Log.d("KategoriViewModel", "Empty Response Body")
                    }
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                    Log.d("KategoriViewModel", "Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
                _kategori.value = emptyList()
                Log.e("KategoriViewModel", "Exception: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
