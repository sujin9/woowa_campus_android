package woowacourse.campus.domain.model

data class Announcement(
    val id: Long,
    val author: String,
    val channel: String,
    val createdAt: String,
    val title: String,
)
