package basics

import log
import org.junit.jupiter.api.Test
import javax.security.sasl.AuthenticationException

class ExampleAsyncTest {

    fun verifyTokenAsync(accessToken: String, cb: (Boolean) -> Unit) {
        // 서비스 API에게 요청을 보내 서비스 토큰을 검증한다.
        cb(false)
    }

    fun getChannelAsync(channelNo: Long, cb: (Channel) -> Unit) {
        // arcus에 채널 정보를 얻는다.
        cb(Channel)
    }

    fun processConnectCommand(channel: Channel, userId: String) {
        // 세션 ID 발급 및 세션 정보를 등록한다.
    }

    fun connectAsync(accessToken: String, channelNo: Long, userId: String) {
        verifyTokenAsync(accessToken) { isAuth ->
            if (!isAuth) throw AuthenticationException()
            getChannelAsync(channelNo) { channel ->
                processConnectCommand(channel, userId)
            }
        }
    }

    @Test
    fun connect() {
        connectAsync("accTkn", 0L, "hello")
    }
}