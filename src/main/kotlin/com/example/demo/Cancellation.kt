package com.example.demo

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val job = launch {
        println("starting launch...[${Thread.currentThread()}]")
        repeat(10) {
            delay(300L)
            println("processing $it...[${Thread.currentThread()}]")
        }
    }
    delay(1000L)
    println("cancelling job...[${Thread.currentThread()}]")
    job.cancelAndJoin()
    println("cancellation is done...[${Thread.currentThread()}]")
}