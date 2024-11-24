package com.example.agrofy_app.viewmodels.pemberdayaan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.pemberdayaan.RetrofitClient
import com.example.agrofy_app.data.api.user.ApiClient
import com.example.agrofy_app.data.api.user.UserPreferences
import com.example.agrofy_app.models.pemberdayaan.ArtikelResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ArtikelViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)
    private val _artikels = MutableStateFlow<List<ArtikelResponse>>(emptyList())
    val artikels: StateFlow<List<ArtikelResponse>> = _artikels.asStateFlow()

    private val _originalArtikels = MutableStateFlow<List<ArtikelResponse>>(emptyList()) // Change to ArtikelResponse

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.getToken.collect { token ->
                ApiClient.setToken(token)
                if (!token.isNullOrEmpty()) {
                    fetchArtikels()
                }
                Log.d("ArtikelViewModel", "Token set: $token")
            }
        }
    }

    private fun fetchArtikels() {
        viewModelScope.launch {
            _isLoading.value = true
            val artikelsDeferred = async { RetrofitClient.instance.getArtikels() }
            val response = artikelsDeferred.await()

            if (response.isSuccessful) {
                _artikels.value = response.body()?.data ?: emptyList()
                _originalArtikels.value = response.body()?.data ?: emptyList()
            } else {
                _error.value = "Error: ${response.code()} - ${response.message()}"
            }
            _isLoading.value = false
        }
    }

    fun filterArtikels(query: String = "", category: String = "Semua") {
        val filteredArtikels = _originalArtikels.value.filter { artikel ->
            (query.isEmpty() || artikel.judulArtikel.contains(query, ignoreCase = true)) &&
                    (category == "Semua" || artikel.namaKategori == category)
        }
        _artikels.value = filteredArtikels
    }
}

