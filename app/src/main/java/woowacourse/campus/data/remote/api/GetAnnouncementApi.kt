package woowacourse.campus.data.remote.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import woowacourse.campus.data.remote.NetworkModule
import woowacourse.campus.data.remote.response.AnnouncementDetailResponse

class GetAnnouncementApi {
    suspend fun getAnnouncement(
        id: Long,
    ): AnnouncementDetailResponse {
        return NetworkModule.client.get {
            url { path("api/announcements/$id") }
        }.body()
    }
}
