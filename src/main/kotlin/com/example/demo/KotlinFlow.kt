package com.example.demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

suspend fun main(args: Array<String>) {
    flow {
        (1..10).forEach {
            delay(100L)
            println("emitting...$it")
            emit(it)
        }
    }
        .onEach { println("emitted value: $it") }
        .filter { it % 3 == 0 }
        .map { it * 2 }
        .collect { println("collected result: $it") }
}