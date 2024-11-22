package com.example.agrofy_app.viewmodels.pemberdayaan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.pemberdayaan.VideoRetrofitClient
import com.example.agrofy_app.models.pemberdayaan.VideoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailVideoViewModel : ViewModel() {
    private val _video = MutableStateFlow<VideoResponse?>(null)
    val video = _video.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun fetchVideoById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = VideoRetrofitClient.instance.getVideoById(id)
                if (response.isSuccessful && response.body()?.data != null) {
                    _video.value = response.body()?.data
                } else {
                    _error.value = "Failed to load video: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

