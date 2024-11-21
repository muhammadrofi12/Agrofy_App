package com.example.agrofy_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.KategoriRetrofitClient
import com.example.agrofy_app.models.KategoriResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KategoriViewModel : ViewModel() {
    private val _kategori = MutableStateFlow<List<KategoriResponse>>(emptyList())
    val kategori: StateFlow<List<KategoriResponse>> = _kategori.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchKategori()
    }

    private fun fetchKategori() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = KategoriRetrofitClient.instance.getKategori()
                if (response.isSuccessful) {
                    response.body()?.let { apiResponse ->
                        _kategori.value = apiResponse.data
                    } ?: run {
                        _error.value = "Response body is empty"
                    }
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
                _kategori.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
