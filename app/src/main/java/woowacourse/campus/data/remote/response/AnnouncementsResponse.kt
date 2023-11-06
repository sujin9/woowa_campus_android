package woowacourse.campus.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementsResponse(
    @SerialName("announcements") val announcements: List<AnnouncementResponse>,
    @SerialName("hasNext") val hasNext: Boolean,
    @SerialName("lastCursorId") val lastCursorId: Long,
)
