package com.learnqa.tests

import com.learnqa.BaseTest
import com.learnqa.generateRandomString
import com.learnqa.models.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class AuthTests: BaseTest() {

    private lateinit var user: User

    @BeforeEach
    fun initUser(){
        user = User(
            username = generateRandomString(6),
            password = generateRandomString(6)
        )
    }

    @Test
    fun singUpTest(){
        authSteps.singUp(user = user)
    }

    @Test
    fun singInTest(){
        authSteps.singUp(user)
        authSteps.singIn(user)
    }

    @Test
    fun authWithoutPasswordTest(){
        authSteps.singUpWithoutPassword(user)
    }
}