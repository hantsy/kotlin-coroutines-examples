package com.example.demo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val result = GlobalScope.async {
        throw UnsupportedOperationException()
    }

    try {
        result.await()
        println("Won't be printed")
    } catch (e: UnsupportedOperationException) {
        println("caught exception: ${e.message}")
    }
}