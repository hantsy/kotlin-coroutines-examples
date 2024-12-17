package com.example.demo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    println("starting....[${Thread.currentThread().name}]")

    GlobalScope.launch {
        println("starting in GlobalScope...[${Thread.currentThread().name}]")
        delay(500L)
        println("finishing in GlobalScope...[${Thread.currentThread().name}]")
    }

    println("continues......[${Thread.currentThread().name}]")

    runBlocking {
        println("starting in runBlocking...[${Thread.currentThread().name}]")
        delay(500L)
        println("finishing in runBlocking...[${Thread.currentThread().name}]")
    }

    println("finishing......[${Thread.currentThread().name}]")

    // delay in launch
    runBlocking {
        println("starting delayInLaunch in runBlocking...[${Thread.currentThread().name}]")
        delayInLaunch()
        println("finishing delayInLaunch in runBlocking...[${Thread.currentThread().name}]")
    }
}

private suspend fun delayInLaunch() = coroutineScope {
    launch(Dispatchers.IO){
        println("starting launch a coroutineScope:[${Thread.currentThread().name}]")
        delay(500L)
        println("Ending launch a coroutineScope:[${Thread.currentThread().name}]")
    }
}