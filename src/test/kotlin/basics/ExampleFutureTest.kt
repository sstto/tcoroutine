package basics

import org.junit.jupiter.api.Test
import java.util.concurrent.CompletableFuture
import javax.security.sasl.AuthenticationException

class ExampleFutureTest {

    fun verifyTokenAsync(accessToken: String): CompletableFuture<Boolean> {
        // 서비스 API에게 요청을 보내 서비스 토큰을 검증한다.
        return CompletableFuture.supplyAsync { false }
    }

    fun getChannelAsync(channelNo: Long): CompletableFuture<Channel> {
        // arcus에 채널 정보를 얻는다.
        return CompletableFuture.supplyAsync { Channel }
    }

    fun processConnectCommand(channel: Channel, userId: String) {
        // 세션 ID 발급 및 세션 정보를 등록한다.
        println("connecting to $userId")
    }

    fun connect(accessToken: String, channelNo: Long, userId: String) {
        verifyTokenAsync(accessToken)
            .thenCompose { isAuth ->
                if (!isAuth) throw AuthenticationException("not valid access token")
                getChannelAsync(channelNo)
            }
            .thenAccept { channel -> processConnectCommand(channel, userId) }
            .whenComplete { _, err -> println(err) }
    }

    @Test
    fun connectTest() {
        connect("accTkn", 0L, "hello")
    }
}