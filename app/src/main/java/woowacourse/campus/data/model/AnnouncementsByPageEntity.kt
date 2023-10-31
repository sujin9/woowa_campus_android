package woowacourse.campus.data.model

data class AnnouncementsByPageEntity(
    val announcements: List<AnnouncementPageEntity>,
    val page: Int,
    val propertySize: Int,
    val totalElements: Int,
    val totalPages: Int,
)