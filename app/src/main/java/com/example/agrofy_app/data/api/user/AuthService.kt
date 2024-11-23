package com.example.agrofy_app.data.api.user

import com.example.agrofy_app.models.user.LoginRequest
import com.example.agrofy_app.models.user.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

//    @GET("profile")
//    suspend fun getProfile(@Header("Authorization") token: String): Response<ProfileResponse>
}

object ApiClient {
    private const val BASE_URL = "https://73zqc05b-3000.asse.devtunnels.ms/"
    var tokenBrearer: String? = null

    fun setToken(newToken: String?) {
        tokenBrearer = newToken
    }


    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

