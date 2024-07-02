package org.example

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.IOException

fun main() {

    val client = OkHttpClient().newBuilder()

    val retrofit = Retrofit.Builder().baseUrl("https://social-wall-learnqa.herokuapp.com")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))     // для конвертации в json и из json
        .client(client.build())    // указать с каким http-клиентом будет вестись работа
        .build().create(SocialWallApi::class.java)

    try {
        val response = retrofit.signUp(
            SignUpRequest(
                "Retrofit", "Password"
            )
        ).execute()

        if (response.isSuccessful) {
            println(response.body()?.message)
        } else {
            println("Request failed with code: ${response.code()}")
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

interface SocialWallApi {

    @POST("/auth/singup")
    fun signUp(
        @Body signUpRequest: SignUpRequest    //запрос
    ): Call<SignUpResponse>   // ответ
}

@Serializable
data class SignUpRequest(
    val username: String, val password: String
)

@Serializable
data class SignUpResponse(
    val message: String
)