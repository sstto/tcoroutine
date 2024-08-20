// This file was automatically generated from coroutines-basics.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleBasic06

import kotlin.concurrent.thread

fun main()  {
    repeat(50_000) { // launch a lot of coroutines
        thread {
            Thread.sleep(5000L)
            print(".")
        }
    }
}
