package com.example.agrofy_app.viewmodels.pemberdayaan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.pemberdayaan.VideoRetrofitClient
import com.example.agrofy_app.models.pemberdayaan.VideoResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VideoViewModel : ViewModel() {
    private val _videos = MutableStateFlow<List<VideoResponse>>(emptyList())
    val videos: StateFlow<List<VideoResponse>> = _videos.asStateFlow()

    private val _originalVideos = MutableStateFlow<List<VideoResponse>>(emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchVideos()
    }

    private fun fetchVideos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val videosDeferred = async { VideoRetrofitClient.instance.getVideos() }
                val response = videosDeferred.await()

                if (response.isSuccessful) {
                    response.body()?.let { apiResponse ->
                        val videoList = apiResponse.data
                        _originalVideos.value = videoList
                        _videos.value = videoList
                    } ?: run {
                        _error.value = "Response body is empty"
                    }
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
                _videos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun filterVideos(query: String = "", category: String = "Semua") {
        val filteredVideos = _originalVideos.value.filter { video ->
            (query.isEmpty() || video.judulVideo.contains(query, ignoreCase = true)) &&
                    (category == "Semua" || video.namaKategori == category)
        }
        _videos.value = filteredVideos
    }

}
