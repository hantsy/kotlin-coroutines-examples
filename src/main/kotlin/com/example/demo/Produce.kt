package com.example.demo

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    myProducer().consumeEach {
        println(it)
    }
}

@ExperimentalCoroutinesApi
fun CoroutineScope.myProducer(): ReceiveChannel<String> = produce {
    (1..10).forEach {
        delay(100)
        send(it.toString())
    }
}