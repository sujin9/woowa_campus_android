package woowacourse.campus.data.mapper

import model.AnnouncementPageResponse
import model.AnnouncementsByPageResponse
import woowacourse.campus.data.model.AnnouncementPageEntity
import woowacourse.campus.data.model.AnnouncementsByPageEntity

object AnnouncementDataMapper {

    fun AnnouncementsByPageResponse.toEntity() = AnnouncementsByPageEntity(
        announcements = announcements.map { it.toEntity() },
        page = page,
        propertySize = propertySize,
        totalElements = totalElements,
        totalPages = totalPages,
    )

    fun AnnouncementPageResponse.toEntity() = AnnouncementPageEntity(
        id = id.toLong(),
        title = title,
        author = author,
        createdAt = createdAt,
    )
}