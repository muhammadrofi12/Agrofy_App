package com.example.agrofy_app.viewmodels.forum

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.user.ApiClient
import com.example.agrofy_app.data.api.user.UserPreferences
import com.example.agrofy_app.data.repository.ForumRepository
import com.example.agrofy_app.models.forum.ForumPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForumViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ForumRepository = ForumRepository()
    private val userPreferences = UserPreferences(application)

    private val _forumPosts = MutableStateFlow<List<ForumPost>>(emptyList())
    val forumPosts: StateFlow<List<ForumPost>> = _forumPosts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.getToken.collect { token ->
                ApiClient.setToken(token)
                if (!token.isNullOrEmpty()) {
                    fetchForumPosts()
                }
                Log.d("ForumViewModel", "Token set: $token")
            }
        }
    }

    private fun fetchForumPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getForumPosts()
            result.onSuccess { posts ->
                posts.forEach { post ->
                    Log.d("ForumPost", "Image URL: ${post.imageResource}")
                }
                _forumPosts.value = posts
                _error.value = null
            }.onFailure { exception ->
                _error.value = exception.message
            }
            _isLoading.value = false
        }
    }
}