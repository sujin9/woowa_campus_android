package woowacourse.campus.domain.model

import java.time.LocalDateTime

data class AnnouncementPage(
    val id: Long,
    val title: String,
    val createdAt: LocalDateTime,
)