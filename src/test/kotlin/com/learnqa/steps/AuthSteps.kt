package com.learnqa.steps

import com.learnqa.client.NetworkClient
import com.learnqa.client.executeRequest
import com.learnqa.model.SingInRequest
import com.learnqa.model.SingUpRequest
import com.learnqa.models.User
import io.qameta.allure.Step
import kotlin.test.assertEquals

class AuthSteps {

    @Step("Регистрация \"{user.username}\"")
    fun singUp(user: User) {
        val response = NetworkClient.client
            .singUp(
                SingUpRequest(
                    username = user.username,
                    password = user.password
                )
            ).executeRequest(200)

        assertEquals(response.first!!.message, "Hello ${user.username}!!")
    }

    @Step("Авторизация \"{user.username}\"")
    fun singIn(user: User) {
        val response = NetworkClient.client
            .singIn(
                SingInRequest(
                    username = user.username,
                    password = user.password
                )
            ).executeRequest(200)

        assertEquals(response.first!!.username, user.username)
    }

    @Step("Регистрация без пароля \"{user.username}\"")
    fun singUpWithoutPassword(user: User) {
        user.password = ""

        val response = NetworkClient.client
            .singUp(
                SingUpRequest(
                    username = user.username,
                    password = user.password
                )
            ).executeRequest(400)

        assertEquals(response.second!!.message, "Password must not be blank")
    }
}