package org.example.guide

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random

fun main(): Unit = runBlocking {
    println("grandparent: ${coroutineContext.job}")
    launch(Dispatchers.Default) {
        println("parent1: $coroutineContext, p: ${coroutineContext.job.parent}")
        launch {
            println("child1: $coroutineContext, p: ${coroutineContext.job.parent}")
        }
        launch {
            println("child2: $coroutineContext, p: ${coroutineContext.job.parent}")
        }
    }
    launch {
        println("parent2: $coroutineContext, p: ${coroutineContext.job.parent}")
        launch {
            println("child3: $coroutineContext, p: ${coroutineContext.job.parent}")
        }
        launch {
            println("child4: $coroutineContext, p: ${coroutineContext.job.parent}")
        }
    }

}