package com.example.agrofy_app.viewmodels.forum

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.repository.ForumRepository
import com.example.agrofy_app.models.forum.Comment
import com.example.agrofy_app.models.forum.ForumPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForumDetailViewModel(
    private val repository: ForumRepository = ForumRepository()
) : ViewModel() {
    private val _forumPost = MutableStateFlow<ForumPost?>(null)
    val forumPost: StateFlow<ForumPost?> = _forumPost.asStateFlow()

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isPostNotFound = MutableStateFlow(false)
    val isPostNotFound: StateFlow<Boolean> = _isPostNotFound.asStateFlow()

    fun loadForumDetails(forumId: Int) {
        Log.d("ForumDetailViewModel", "Loading forum details for postId: $forumId")
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _isPostNotFound.value = false

            try {
                val response = repository.getForumPostDetail(forumId)
                response.onSuccess { post ->
                    Log.d("ForumDetailViewModel", "Successfully fetched post: ${post.id}")
                    _forumPost.value = post

                    val commentsResponse = repository.getForumComments(forumId)
                    commentsResponse.onSuccess { comments ->
                        Log.d("ForumDetailViewModel", "Fetched ${comments.size} comments")
                        _comments.value = comments
                    }.onFailure { exception ->
                        Log.e("ForumDetailViewModel", "Failed to load comments: ${exception.message}")
                        _error.value = "Failed to load comments: ${exception.message}"
                    }
                }.onFailure { exception ->
                    Log.e("ForumDetailViewModel", "Post not found: ${exception.message}")
                    _isPostNotFound.value = true
                    _error.value = "Post not found: ${exception.message}"
                }
            } catch (e: Exception) {
                Log.e("ForumDetailViewModel", "Unexpected error: ${e.message}")
                _error.value = "Unexpected error: ${e.message}"
            } finally {
                _isLoading.value = false
                Log.d("ForumDetailViewModel", "Loading complete")
            }
        }
    }

    fun addComment(forumId: Int, commentText: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.addComment(forumId, commentText)

            result.onSuccess {
                loadForumDetails(forumId) // Refresh comments after adding
            }.onFailure { exception ->
                _error.value = exception.message
            }

            _isLoading.value = false
        }
    }

    fun setError(message: String) {
        _error.value = message
        _isLoading.value = false
        _isPostNotFound.value = false
    }

    fun refreshDetails(forumId: Int) {
        loadForumDetails(forumId)
    }
}
