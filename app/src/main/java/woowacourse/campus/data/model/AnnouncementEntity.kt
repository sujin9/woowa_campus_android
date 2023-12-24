package woowacourse.campus.data.model

data class AnnouncementEntity(
    val id: Long,
    val author: String,
    val channel: String,
    val createdAt: String,
    val title: String,
)
