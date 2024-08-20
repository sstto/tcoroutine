package basics

import org.junit.jupiter.api.Test
import javax.security.sasl.AuthenticationException

class ExampleTest {

    fun verifyToken(accessToken: String): Boolean {
        // 서비스 API에게 요청을 보내 서비스 토큰을 검증한다.
        return true
    }

    fun getChannel(channelNo: Long): Channel {
        // arcus에 채널 정보를 얻는다.
        return Channel
    }

    fun processConnectCommand(channel: Channel, userId: String) {
        // 세션 ID 발급 및 세션 정보를 등록한다.
        println("connecting to $userId")
    }

    fun connect(accessToken: String, channelNo: Long, userId: String) {
        if (!verifyToken(accessToken)) throw AuthenticationException("not valid access token")
        val channel = getChannel(channelNo)
        processConnectCommand(channel, userId)
    }

    @Test
    fun connect() {
        connect("accTkn", 0L, "localhost")
    }

}

object Channel