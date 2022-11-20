package com.example.demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

suspend fun main(args: Array<String>) {
    flow {
        (1..10).forEach {
            delay(100L)
            println("emitting...$it")
            emit(it)
        }
    }
        .map { it * 2 }
        .collect { println("collected result: $it") }
}