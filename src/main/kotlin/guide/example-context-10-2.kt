// This file was automatically generated from coroutine-context-and-dispatchers.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleContext102

import kotlinx.coroutines.*

class Activity {
    private val mainScope = CoroutineScope(Dispatchers.Default + SupervisorJob()) // use Default for test purposes
    
    fun destroy() {
        mainScope.cancel()
    }

    fun logCurrentState(prefix: String) {
        val job = mainScope.coroutineContext.job
        println("$prefix: isActive=${job.isActive}, isCancelled=${job.isCancelled}, isCompleted=${job.isCompleted}")
    }

    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        repeat(10) { i ->
            mainScope.launch {
                try {
                    delay((i + 1) * 200L) // variable delay 200ms, 400ms, ... etc
                    println("Coroutine $i is done")
                } catch (e: CancellationException) {
                    logCurrentState(i.toString())
                }
            }
        }
    }
} // class Activity ends

fun main() = runBlocking<Unit> {
    val activity = Activity()
    activity.doSomething() // run test function
    println("Launched coroutines")
    delay(500L) // delay for half a second
    println("Destroying activity!")
    activity.destroy() // cancels all coroutines
    delay(1000) // visually confirm that they don't work
    activity.logCurrentState("end")
}
