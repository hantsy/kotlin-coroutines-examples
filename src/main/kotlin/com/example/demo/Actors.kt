package com.example.demo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking

// see: https://github.com/domnikl/kotlin-coroutine-examples
// this starts an actor that runs on the default dispatcher and records every message send to it
// when it is closed, it will print all messages and stop

@OptIn(ObsoleteCoroutinesApi::class)
fun main()  = runBlocking<Unit> {
    val printActor = actor<String>(Dispatchers.Default) {
        val messages = mutableListOf<String>()

        for (msg in channel) {
            messages.add(msg)
        }

        println("from thread ${Thread.currentThread()}")
        println(messages.joinToString(", "))
    }

    repeat(100) { i ->
        printActor.send(i.toString())
    }

    printActor.close()
}