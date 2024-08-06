package com.learnqa

import kotlin.random.Random

fun generateRandomString(randomStringLength: Int): String {
    val charPool: List<Char> = ('a'..'z').toList()
    return (1..randomStringLength)
        .map { _ -> Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}