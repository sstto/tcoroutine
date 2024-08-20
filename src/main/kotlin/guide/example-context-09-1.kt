// This file was automatically generated from coroutine-context-and-dispatchers.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleContext09

import kotlinx.coroutines.*

@OptIn(ExperimentalStdlibApi::class)
fun main() = runBlocking<Unit> {
    val job = launch(Dispatchers.Default + CoroutineName("test") + SupervisorJob()) {
        println("I'm working in coroutineContext ")
        println("job: ${coroutineContext[Job]}")
        println("dispatcher: ${coroutineContext[CoroutineDispatcher]}")
        println("name: ${coroutineContext[CoroutineName]}")
    }
    job.join()
}

