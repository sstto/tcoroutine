// This file was automatically generated from exception-handling.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleExceptions06

import kotlinx.coroutines.*
import java.io.*

fun main() {
    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    with(scope) {
        val job1 = launch {
            throw Exception("Some jobs just want to watch the world burn")
        }
        val job2 = launch {
            delay(3000)
            println("I've done something extremely useful")
        }
    }
    scope.coroutineContext[Job]?.let { job ->
        runBlocking { job.children.forEach { it.join() } }
    } // `job1.join()` will throw, so `it.join()` should actually be in a `try/catch` block
}
