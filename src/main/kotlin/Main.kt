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

fun main() {

    val client = OkHttpClient().newBuilder()

    val retrofit = Retrofit.Builder().baseUrl("https://social-wall-learnqa.herokuapp.com")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))     // для конвертации в json и из json
        .client(client.build())    // указать с каким http-клиентом будет вестись работа
        .build().create(SocialWallApi::class.java)

    println(
       retrofit.singUp(
            SingUpRequest(
                "Retrofit", "Password"
            )
        ).execute().body()!!.message
    )
}

interface SocialWallApi {

    @POST("/auth/singup")
    fun singUp(
        @Body singUpRequest: SingUpRequest    //запрос
    ): Call<SingUpResponse>   // ответ
}

@Serializable
data class SingUpRequest(
    val username: String, val password: String
)

@Serializable
data class SingUpResponse(
    val message: String
)