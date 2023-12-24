package woowacourse.campus.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementResponse(
    @SerialName("id") val id: Int,
    @SerialName("author") val author: String,
    @SerialName("slackChannel") val channel: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("title") val title: String,
)
