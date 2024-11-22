package com.example.agrofy_app.viewmodels.pemberdayaan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.pemberdayaan.RetrofitClient
import com.example.agrofy_app.models.pemberdayaan.ArtikelResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ArtikelViewModel : ViewModel() {
    private val _artikels = MutableStateFlow<List<ArtikelResponse>>(emptyList())
    val artikels: StateFlow<List<ArtikelResponse>> = _artikels.asStateFlow()

    private val _originalArtikels = MutableStateFlow<List<ArtikelResponse>>(emptyList()) // Change to ArtikelResponse

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchArtikels()
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

