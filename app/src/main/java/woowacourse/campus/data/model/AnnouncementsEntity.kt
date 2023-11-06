package woowacourse.campus.data.model

data class AnnouncementsEntity(
    val announcements: List<AnnouncementEntity>,
    val hasNext: Boolean,
    val lastCursorId: Long,
)
