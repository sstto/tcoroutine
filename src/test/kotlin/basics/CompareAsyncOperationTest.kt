package basics

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import threadPool
import java.time.Duration
import java.util.concurrent.CompletableFuture

class CompareAsyncOperationTest {

    val list = listOf(100, 200, 300, 400, 500)

    val balance = 2000

    @Test
    fun `동기 연산`() {
        val total = list.sum()
        if (balance - total < 0) throw IllegalStateException("Balance can't be negative")
        println(balance - total)
    }

    @Test
    fun `CompletableFuture 연산`() {
        val future = CompletableFuture.supplyAsync {
            list.sum()
        }.thenApply { total ->
            if (balance - total < 0) throw IllegalStateException("Balance can't be negative")
            balance - total
        }
        println(future.get())
    }

    @Test
    fun `Callback 연산`() {

    }

    @Test
    fun `리액티브 스트림 reactor 연산`() {
        Flux.fromIterable(list)
            .reduce { acc, e -> acc + e }
            .subscribe(::println)

        Thread.sleep(1000)
    }

    @Test
    fun `코루틴 연산`() {

    }

}
