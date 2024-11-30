package com.example.agrofy_app.data.api.user

import com.example.agrofy_app.models.user.LoginRequest
import com.example.agrofy_app.models.user.LoginResponse
import com.example.agrofy_app.models.user.ProfileResponse
import com.example.agrofy_app.models.user.RegisterRequest
import com.example.agrofy_app.models.user.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface AuthService {
    @POST("api/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("api/v1/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("api/v1/profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<ProfileResponse>

    @PUT("api/v1/profile")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Body profileData: Map<String, String>
    ): Response<ProfileResponse>

    @PUT("api/v1/profile/password")
    suspend fun editPassword(
        @Header("Authorization") token: String,
        @Body passwordData: Map<String, String>
    ): Response<ProfileResponse>

    @Multipart
    @PUT("api/v1/profile/updatefoto")
    suspend fun uploadProfileImage(
        @Header("Authorization") token: String,
        @Part foto: MultipartBody.Part
    ): Response<ProfileResponse>


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

