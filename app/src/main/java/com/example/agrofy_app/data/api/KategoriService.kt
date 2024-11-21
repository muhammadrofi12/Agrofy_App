package com.example.agrofy_app.data.api

import com.example.agrofy_app.models.ApiResponse
import com.example.agrofy_app.models.KategoriResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface KategoriApiService {
    @GET("api/v1/getkategori")
    suspend fun getKategori(): retrofit2.Response<ApiResponse<List<KategoriResponse>>>
}

object KategoriRetrofitClient {
    private const val BASE_URL = "https://73zqc05b-3000.asse.devtunnels.ms/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    val instance: KategoriApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(KategoriApiService::class.java)
    }
}