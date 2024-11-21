package com.example.agrofy_app.models

data class ApiResponse<T>(
    val msg: String,
    val data: T,
    val pagination: Pagination
)

data class Pagination(
    val totalData: Int,
    val totalPages: Int,
    val currentPage: Int,
    val perPage: Int
)

