package com.example.agrofy_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agrofy_app.data.api.AdminRetrofitClient
import com.example.agrofy_app.data.api.RetrofitClient
import com.example.agrofy_app.models.AdminResponse
import com.example.agrofy_app.models.ArtikelResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailArtikelViewModel : ViewModel() {
    private val _artikel = MutableStateFlow<ArtikelResponse?>(null)
    val artikel = _artikel.asStateFlow()

    private val _admins = MutableStateFlow<List<AdminResponse>>(emptyList()) // Menyimpan data admin
    val admins = _admins.asStateFlow()

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
                    fetchAdmins() // Panggil API Admin setelah artikel ditemukan
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

    // Fungsi untuk mengambil data admin
    private fun fetchAdmins() {
        viewModelScope.launch {
            try {
                val response = AdminRetrofitClient.instance.getAdmin()
                if (response.isSuccessful) {
                    _admins.value = response.body()?.data ?: emptyList()
                } else {
                    _error.value = "Failed to load admins"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            }
        }
    }

    // Fungsi untuk mendapatkan nama admin berdasarkan userId
    fun getAdminNameById(userId: Int): String? {
        return _admins.value.find { it.id == userId }?.nama_lengkap
    }
}

