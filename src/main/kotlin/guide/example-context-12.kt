package org.example.guide

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random

fun main(): Unit = runBlocking {
    val num = AtomicLong()
    val cancel = AtomicLong()
    val exception = AtomicLong()
    val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        exception.incrementAndGet()
        println("bye: ${context[CoroutineName]?.name}: ${throwable.message}")
    }

    with(CoroutineScope(SupervisorJob() + exceptionHandler + Dispatchers.Default)) {
            val jobs: List<Job> = List(1_000_000) {
                launch(
                    CoroutineName("#$it"), // CoroutineContext
                    CoroutineStart.LAZY
                ) {
                    try {
                        delay(Random.nextLong(1000))
                        if (it % 10 == 0) { throw Exception("No comments") }
                        println("Hello from coroutine $it!")
                        num.incrementAndGet()
                    } catch (e: CancellationException) {
                        cancel.incrementAndGet()
                    }
                }
            }

            jobs.forEach { it.start() }
            jobs.joinAll()
    }
    println("total: $num")
    println("exception: $exception")
    println("canceled: $cancel")
}