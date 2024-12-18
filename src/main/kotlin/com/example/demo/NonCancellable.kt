package com.example.demo

import kotlinx.coroutines.*

/**
 *
 * The primary use case for withContext(NonCancellable) is to perform cleanup operations or execute critical code
 * that must complete regardless of the cancellation status of the parent coroutine. This is particularly useful
 * in finally blocks, where resources need to be released or data needs to be saved before a coroutine terminates.
 *
 * Important Considerations
 *
 *   *  Not for general use:
 *     withContext(NonCancellable) should not be used for regular operations.
 *     Overusing it can lead to unpredictable behavior and loss of control over coroutine execution.
 *   *  Cancellation checks:
 *     While the block itself is non-cancellable, it does not prevent suspension points within the block from
 *     checking for cancellation if they are designed to do so.
 *   *  Parent-child relationship:
 *     Using launch(NonCancellable) severs the parent-child relationship between coroutines.
 *     The parent will not wait for the child to complete, nor will it be notified if the child crashes. This is different from withContext(NonCancellable), which preserves the parent-child relationship.
 *   *  Resource management:
 *     Acquisition and release of resources can be wrapped into withContext(NonCancellable) block.
 */

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(5) { i ->
                println("Job: I'm sleeping $i ...: : ${Thread.currentThread().name}")
                delay(500)
            }
        } finally {
            withContext(NonCancellable) {
                println("Job: I'm running finally: ${Thread.currentThread().name}")
                delay(1000)
                println("Job: And I've just delayed for 1 sec because I'm non-cancellable: : ${Thread.currentThread().name}")
            }
        }
    }
    delay(1300)
    println("Main: I'm tired of waiting and cancel it!: : ${Thread.currentThread().name}")
    job.cancelAndJoin()
    println("Main: Now I can quit.: : ${Thread.currentThread().name}")
}