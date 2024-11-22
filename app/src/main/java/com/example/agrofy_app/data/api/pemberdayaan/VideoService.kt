package com.example.agrofy_app.data.api.pemberdayaan

import com.example.agrofy_app.models.ApiResponse
import com.example.agrofy_app.models.pemberdayaan.VideoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface VideoApiService {
    @GET("api/v1/getvideo")
    suspend fun getVideos(): Response<ApiResponse<List<VideoResponse>>>

    // Mengambil satu data video by id
    @GET("api/v1/getvideodetail/{id}")
    suspend fun getVideoById(@Path("id") id: Int): Response<ApiResponse<VideoResponse>>
}

object VideoRetrofitClient {
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

    val instance: VideoApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(VideoApiService::class.java)
    }
}
