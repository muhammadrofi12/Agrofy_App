package com.example.agrofy_app.viewmodels.forum

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.repository.ForumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class AddForumViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ForumRepository = ForumRepository()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _postAdded = MutableStateFlow(false)
    val postAdded: StateFlow<Boolean> = _postAdded.asStateFlow()

    fun addForum(postText: String, imageFile: File?) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = repository.addForum(postText, imageFile)

            result.onSuccess {
                _postAdded.value = true
                _error.value = null
            }.onFailure { exception ->
                _postAdded.value = false
                _error.value = exception.message
            }

            _isLoading.value = false
        }
    }
}