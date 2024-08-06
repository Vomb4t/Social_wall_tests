package com.learnqa.model

import kotlinx.serialization.Serializable

@Serializable
data class SingInRequest( // исправил название на SignInRequest
    val username: String,
    val password: String
)

@Serializable
data class SingInResponse(
    val username: String,
    val token: String
)