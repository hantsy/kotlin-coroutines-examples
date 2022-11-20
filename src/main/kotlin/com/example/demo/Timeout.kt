package com.example.demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main(args: Array<String>) = runBlocking {
//    withTimeout(1000L) {
//        repeat(30) {
//            delay(300L)
//            println("Processing with timeout $it")
//        }
//    }

    val result = withTimeoutOrNull(1000L) {
        repeat(30) {
            delay(300L)
            println("Processing with withTimeoutOrNull $it")
        }
        "Finished"
    }
    println("The final processing result is $result")

}