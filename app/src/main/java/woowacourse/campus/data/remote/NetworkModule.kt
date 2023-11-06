package woowacourse.campus.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

object NetworkModule {
    private const val baseUrl = "https://4868ef12-e70d-404f-a10e-7ddf55eb9efa.mock.pstmn.io"

    val client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            header("password", "asdf")
            url(baseUrl)
        }
    }
}
