package com.example.agrofy_app.data.api.forum

import com.example.agrofy_app.data.api.user.ApiClient.tokenBrearer
import com.example.agrofy_app.models.forum.ForumDetailResponse
import com.example.agrofy_app.models.forum.ForumResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

data class AddCommentRequest(
    val balasan: String
)

interface ForumApiService {
    @GET("api/v1/getkomunitas")
    suspend fun getForum(): Response<ForumResponse>

    @GET("api/v1/getkomunitasbalasan/{id}")
    suspend fun getForumPostAndComments(@Path("id") forumId: Int): Response<ForumDetailResponse>

    @POST("api/v1/tambahbalasan/{id}")
    suspend fun addComment(
        @Path("id") forumId: Int,
        @Body commentBody: AddCommentRequest
    ): Response<Any>
}

// Retrofit Client Configuration
object ForumRetrofitClient {
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

    val instance: ForumApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForumApiService::class.java)
    }
}



