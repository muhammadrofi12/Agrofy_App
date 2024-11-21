package com.example.agrofy_app.data.api

import android.util.Log
import com.example.agrofy_app.models.AdminResponse
import com.example.agrofy_app.models.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface AdminApiService {
    @GET("api/v1/getadmin")
    suspend fun getAdmin(): Response<ApiResponse<List<AdminResponse>>>

}

object AdminRetrofitClient {
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

    val instance: AdminApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.d("AdminRetrofitClient", "Base URL: $BASE_URL")
        retrofit.create(AdminApiService::class.java)
    }
}