package org.example.guide

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random

val exceptionHandler = CoroutineExceptionHandler { context, throwable -> println("bye: ${context[CoroutineName]?.name}: ${throwable.message}") }

val crtScope = CoroutineScope(Dispatchers.Default)

fun main(): Unit = runBlocking(exceptionHandler) {
    println(coroutineContext)
    val num = AtomicLong()
    val fail = AtomicLong()
    with(crtScope) {
        val job = launch( SupervisorJob() + exceptionHandler) {
            val jobs = List(1_000_000) {
                launch(
                    CoroutineName("#$it"), // CoroutineContext
                    CoroutineStart.LAZY
                ) {
                    try {
                        delay(Random.nextLong(1000))
                        if (it % 10 == 0) {
                            throw Exception("No comments")
                        }
                        println("Hello from coroutine $it!")
                        num.incrementAndGet()
                    } catch (e: CancellationException) {
                        fail.incrementAndGet()
                        throw e
                    }

                }
            }
            jobs.forEach { try { it.start() } catch (e: Exception) { println(coroutineContext.job.isCancelled)} }
        }
        job.join()

        println("total: $num")
        println("fail: $fail")
    }

}