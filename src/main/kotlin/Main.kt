package org.example

import kotlinx.coroutines.*
import javax.swing.SwingUtilities
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

fun main(): Unit = runBlocking {
    launch {
        delay(1000)
        println("hello")
    }

    world()
}

suspend fun world() = coroutineScope {
    launch {
        delay(200)
        throw IllegalStateException()
    }
    launch {
        delay(400)
        println("world")
    }
}

class AuthUser(val name: String) : AbstractCoroutineContextElement(AuthUser) {
    companion object : CoroutineContext.Key<AuthUser>
}

private class SwingContinuation<T>(val cont: Continuation<T>) : Continuation<T> {
    override val context: CoroutineContext get() = cont.context
    override fun resumeWith(result: Result<T>) {
        SwingUtilities.invokeLater { cont.resumeWith(result) }
    }
}

object Swing : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> = SwingContinuation(continuation)
}