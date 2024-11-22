package com.example.agrofy_app.models.pemberdayaan

import com.google.gson.annotations.SerializedName

data class ArtikelResponse(
    val id: Int,
    @SerializedName("judul_artikel") val judulArtikel: String,
    val deskripsi: String,
    val thumbnail: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("kategori_id") val kategoriId: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("nama_lengkap") val namaLengkap: String,
    @SerializedName("nama_kategori") val namaKategori: String
)