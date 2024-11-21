package com.example.agrofy_app.models

data class Videos(
    val id: Int,
    val judul: String,
    val sub_judul: String,
    val deskripsi: String,
    val tanggal: Int,
    val kategori: String,
    val duration: String,
    val photo: Int,
    val file_video: String,
    val author: String,
)