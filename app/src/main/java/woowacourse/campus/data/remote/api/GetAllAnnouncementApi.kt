package woowacourse.campus.data.remote.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import woowacourse.campus.data.remote.NetworkModule
import woowacourse.campus.data.remote.response.AnnouncementsResponse

class GetAllAnnouncementApi {
    suspend fun getAnnouncements(
        lastItemId: Long?,
        size: Int,
    ): AnnouncementsResponse {
        return NetworkModule.client.get {
            url("api/announcements/cursor")
            parameter("id", lastItemId)
            parameter("size", size)
        }.body()
    }
}
