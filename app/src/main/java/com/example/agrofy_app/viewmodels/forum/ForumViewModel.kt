package com.example.agrofy_app.viewmodels.forum

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.repository.ForumRepository
import com.example.agrofy_app.models.forum.ForumPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForumViewModel(
    private val repository: ForumRepository = ForumRepository()
) : ViewModel() {
    private val _forumPosts = MutableStateFlow<List<ForumPost>>(emptyList())
    val forumPosts: StateFlow<List<ForumPost>> = _forumPosts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchForumPosts()
    }

    fun fetchForumPosts() {
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


    fun refreshPosts() {
        fetchForumPosts()
    }
}