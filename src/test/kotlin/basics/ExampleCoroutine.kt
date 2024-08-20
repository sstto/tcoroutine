package basics

import kotlinx.coroutines.*
import java.util.Timer
import javax.security.sasl.AuthenticationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class ExampleCoroutine {

    suspend fun verifyToken(accessToken: String): Boolean {
        // 서비스 API에게 요청을 보내 서비스 토큰을 검증한다.
        return true
    }

    suspend fun getChannel(channelNo: Long): Channel {
        // arcus에 채널 정보를 얻는다.
        return Channel
    }

    fun processConnectCommand(channel: Channel, userId: String) {
        // 세션 ID 발급 및 세션 정보를 등록한다.
        println("connecting to $userId")
    }

    val crtScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    fun connect(accessToken: String, channelNo: Long, userId: String): Job = crtScope.run  {
        launch { // fire & forgot
            // label 0
            if (!verifyToken(accessToken)) throw AuthenticationException("not valid access token")
            // label 1
            val channel = getChannel(channelNo)
            // label 2
            processConnectCommand(channel, userId)
        }
    }

    suspend fun connectA(accessToken: String, channelNo: Long, userId: String) {
        // state 0
        if (!verifyToken(accessToken)) throw AuthenticationException("not valid access token")
        // state 1
        val channel = getChannel(channelNo)
        // state 2
        processConnectCommand(channel, userId)
        delay(1000)
    }

    suspend fun loop() {
        val accessToken = ""
        val channelNos = emptyList<Long>()

        for (i in 1..10) {
            getChannel(i.toLong())
        }

        try {
            if (!verifyToken(accessToken)) throw AuthenticationException("not valid access token")
        } catch (ex: AuthenticationException) {
            // TODO
        }

        channelNos.forEach { getChannel(it) }

    }

}