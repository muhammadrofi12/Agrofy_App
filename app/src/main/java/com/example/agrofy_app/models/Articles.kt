package com.example.agrofy_app.models

data class Articles(
    val id: Int,
    val judul: String,
    val deskripsi: String,
    val tanggal: Int,
    val kategori: String,
    val photo: Int,
    val author: String,
)
