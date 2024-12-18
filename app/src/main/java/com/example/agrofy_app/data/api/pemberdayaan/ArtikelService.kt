package com.example.agrofy_app.data.api.pemberdayaan

import com.example.agrofy_app.data.api.user.ApiClient.tokenBrearer
import com.example.agrofy_app.models.ApiResponse
import com.example.agrofy_app.models.pemberdayaan.ArtikelResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ArtikelApiService {
    @GET("api/v1/getartikel")
    suspend fun getArtikels(): Response<ApiResponse<List<ArtikelResponse>>>

    // Mengambil satu data artikel by id
    @GET("api/v1/getartikeldetail/{id}")
    suspend fun getArtikelById(@Path("id") id: Int): Response<ApiResponse<ArtikelResponse>>
}

object ArtikelRetrofitClient {
    private const val BASE_URL = "https://73zqc05b-3000.asse.devtunnels.ms/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        tokenBrearer?.let {
            requestBuilder.addHeader("Authorization", it)
        }
        chain.proceed(requestBuilder.build())
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    val instance: ArtikelApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtikelApiService::class.java)
    }
}
