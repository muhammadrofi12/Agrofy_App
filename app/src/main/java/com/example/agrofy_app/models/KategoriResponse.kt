package com.example.agrofy_app.models

import com.google.gson.annotations.SerializedName

data class KategoriResponse(
    val id: Int,
    @SerializedName("nama_kategori") val namaKategori: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)
