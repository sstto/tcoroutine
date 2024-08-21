// This file was automatically generated from coroutine-context-and-dispatchers.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleContext101

import kotlinx.coroutines.*

class Activity {
    private val mainScope = CoroutineScope(Dispatchers.Default) // use Default for test purposes
    
    fun destroy() {
        mainScope.cancel()
    }

    fun isActive(): Boolean {
        return mainScope.isActive
    }

    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        repeat(10) { i ->
            mainScope.launch {
                while (true) {
                    try {
                        delay((i + 1) * 200L) // variable delay 200ms, 400ms, ... etc
                        if (i == 2) throw Exception()
                        println("Coroutine $i is done")
                    } catch (e: CancellationException) {
                        println("catch.. ${e.message}")
                    }
                }
            }
        }
    }
} // class Activity ends

fun main() = runBlocking<Unit> {
    val activity = Activity()
    activity.doSomething() // run test function
    println("Launched coroutines")
    println(activity.isActive())
    delay(800L) // delay for half a second
    println(activity.isActive())
    delay(10000) // visually confirm that they don't work
}
