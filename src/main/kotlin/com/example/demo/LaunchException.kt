package com.example.demo

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("handling exception...$throwable")
    }

    val job = GlobalScope.launch(handler) {
        throw UnsupportedOperationException()
    }

    job.join()
}