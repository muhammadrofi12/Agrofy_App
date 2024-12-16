package com.example.agrofy_app.models

data class Limbah(
    val id: Int,
    val nama: String,
    val photo: Int,
    val deskripsi: String,
    val tgglMasuk: Int, // bisa tggl_progress kalo limbah di bagian progress
    val tgglKeluar: Int, // bisa tggl_selesai kalo limbah di bagian progress
    val status: String,
    val kategori: String
)
