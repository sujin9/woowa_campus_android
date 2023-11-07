package woowacourse.campus.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementDetailResponse(
    @SerialName("author") val author: String,
    @SerialName("content") val content: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("title") val title: String,
)
