package com.learnqa.models

import com.learnqa.model.GetPostResponse
import kotlin.properties.Delegates

class User(
    val username: String,
    var password: String
) {
    lateinit var token: String

    override fun toString(): String = "Username $username, Password $password"
}

class PostModel(
    val text: String
) {
    var id by Delegates.notNull<Int>()

    lateinit var reactions: GetPostResponse.Post.Reactions
}