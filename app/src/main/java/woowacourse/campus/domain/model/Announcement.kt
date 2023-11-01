package woowacourse.campus.domain.model

data class Announcement(
    val id: Int,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: String
)
