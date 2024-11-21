package com.example.agrofy_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.AdminRetrofitClient
import com.example.agrofy_app.models.AdminResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdminViewModel : ViewModel() {
    private val _admins = MutableStateFlow<List<AdminResponse>>(emptyList())
    val admins = _admins.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun fetchAdmins() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = AdminRetrofitClient.instance.getAdmin()
                if (response.isSuccessful) {
                    // Filter admin by role
                    _admins.value = response.body()?.data?.filter { it.role == "admin" } ?: emptyList()
                } else {
                    _error.value = "Failed to load admins: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Function to get admin name by userId
    fun getAdminNameById(userId: Int): String? {
        return _admins.value.find { it.id == userId }?.nama_lengkap
    }
}

