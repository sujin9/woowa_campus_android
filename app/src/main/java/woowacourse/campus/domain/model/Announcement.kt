package woowacourse.campus.domain.model

data class Announcement(
    val id: Long,
    val author: String,
    val content: String,
    val createdAt: String,
    val title: String,
)
