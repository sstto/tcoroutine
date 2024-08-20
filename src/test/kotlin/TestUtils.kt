import kotlinx.coroutines.test.StandardTestDispatcher
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

val testDispatcher = StandardTestDispatcher()

val threadPool = Executors.newFixedThreadPool(100)

inline fun tMeasureTimeMillis(block: () -> Unit): Unit {
    val elapsedTime = measureTimeMillis {
        block()
    }
    println("Elapsed time: $elapsedTime ms")
}

inline fun log(s: String) {
    println("[${Thread.currentThread().name}] $s")
}