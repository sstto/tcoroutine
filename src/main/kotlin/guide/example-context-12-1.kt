package org.example.guide

import kotlinx.coroutines.*
import kotlin.random.Random

fun main(): Unit = runBlocking {

    val exceptionHandler = CoroutineExceptionHandler { context, throwable -> println("bye: ${context[CoroutineName]?.name}: ${throwable.message}") }

    val jobs: List<Job> = List(1_000_000) {
        launch(
            Dispatchers.Default + CoroutineName("#$it") + exceptionHandler,
            CoroutineStart.LAZY
        ) {
            delay(Random.nextLong(1000))
            if (it % 10 == 0) { throw Exception("No comments") }
            println("Hello from coroutine $it!")
        }
    }

    jobs.forEach { it.start() }
}