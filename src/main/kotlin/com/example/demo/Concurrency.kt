package com.example.demo

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

fun main(args: Array<String>) = runBlocking{
    val hello= async { hello() }
    val world = async { world() }
    printCurrentTime("main")
    val result = hello.await() + " " + world.await()
    printCurrentTime("main-result")
    println("result is $result")
}

suspend fun hello(): String {
    println("calculating hello...")
    delay(500L)
    printCurrentTime("hello")
    return "Hello"
}

suspend fun world(): String {
    println("calculating world...")
    delay(500L)
    printCurrentTime("world")
    return "world"
}

fun printCurrentTime(prefix: String ) {
    println("[$prefix]current time is "+ LocalDateTime.now())
}

