package com.example.agrofy_app.viewmodels.pemberdayaan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.pemberdayaan.RetrofitClient
import com.example.agrofy_app.models.pemberdayaan.ArtikelResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailArtikelViewModel : ViewModel() {
    private val _artikel = MutableStateFlow<ArtikelResponse?>(null)
    val artikel = _artikel.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    // Fungsi untuk mengambil artikel berdasarkan ID
    fun fetchArtikelById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitClient.instance.getArtikelById(id)
                if (response.isSuccessful && response.body()?.data != null) {
                    _artikel.value = response.body()?.data
                } else {
                    _error.value = "Failed to load article: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

}

