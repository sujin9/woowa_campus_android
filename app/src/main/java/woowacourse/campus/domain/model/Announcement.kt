package woowacourse.campus.domain.model

import java.time.LocalDateTime

data class Announcement(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
)