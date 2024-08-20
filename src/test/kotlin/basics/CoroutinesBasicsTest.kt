package basics

import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import tMeasureTimeMillis
import threadPool
import java.util.concurrent.CompletableFuture
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CoroutinesBasicsTest {

    @Test
    fun `동기 연산`() {
        tMeasureTimeMillis {
            val jobs = List(50_00) {
                threadPool.submit {
                    Thread.sleep(5000L)
                    println(".")
                }
            }
            jobs.forEach { it.get() }
        }
    }

    @Test
    fun `비동기 연산`() = runTest {
        tMeasureTimeMillis {
            val jobs = List(50_00) {
                launch(threadPool.asCoroutineDispatcher()) {
                    delay(5000L)
                    println(".")
                }
            }
            jobs.joinAll()
        }
    }

    @Test
    fun asdf() = runTest {
        val ctx1 = Dispatchers.IO + Job()
        val ctx2 = Dispatchers.Default + Job()
        println(ctx1 + ctx2)
    }

    fun completableTest() = runBlocking {
        CompletableFuture.supplyAsync { println("completableTest") }.await()

    }

    suspend fun <T> CompletableFuture<T>.await(): T =
        suspendCoroutine<T> { cont: Continuation<T> ->
            whenComplete { result, exception ->
                if (exception == null) // the future has been completed normally
                    cont.resume(result)
                else // the future has completed with an exception
                    cont.resumeWithException(exception)
            }
        }

}