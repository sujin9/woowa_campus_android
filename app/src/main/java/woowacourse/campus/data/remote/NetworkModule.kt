package woowacourse.campus.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object NetworkModule {
    private const val baseUrl = "http://192.168.6.25:8080/"

    @OptIn(ExperimentalSerializationApi::class)
    val client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(
                Json{
                    this.ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }

        defaultRequest {
            header("Authorization", "Basic MTIzNA==")
            url(baseUrl)
        }
    }
}
