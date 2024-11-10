package com.example.agrofy_app.models

data class Limbah(
    val id: Int,
    val nama: String,
    val photo: Int,
    val berat: Int,
    val deskripsi: String,
    val tggl_masuk: Int, // bisa tggl_progress kalo limbah di bagian progress
    val tggl_keluar: Int, // bisa tggl_selesai kalo limbah di bagian progress
)
