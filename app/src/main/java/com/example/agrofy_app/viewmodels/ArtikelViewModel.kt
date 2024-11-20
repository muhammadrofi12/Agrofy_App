package com.example.agrofy_app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.RetrofitClient
import com.example.agrofy_app.models.ArtikelResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArtikelViewModel : ViewModel() {
    private val _artikels = MutableStateFlow<List<ArtikelResponse>>(emptyList())
    val artikels: StateFlow<List<ArtikelResponse>> = _artikels.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchArtikels()
    }

    fun fetchArtikels() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = RetrofitClient.instance.getArtikels()
                if (response.isSuccessful) {
                    response.body()?.let { apiResponse ->
                        _artikels.value = apiResponse.data
                    } ?: run {
                        _error.value = "Response body is empty"
                    }
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                Log.e("ArtikelViewModel", "Error fetching artikels", e)
                _error.value = e.message ?: "Unknown error occurred"
                _artikels.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
