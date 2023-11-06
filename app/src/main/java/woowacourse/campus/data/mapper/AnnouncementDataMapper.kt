package woowacourse.campus.data.mapper

import woowacourse.campus.data.model.AnnouncementDetailEntity
import woowacourse.campus.data.model.AnnouncementEntity
import woowacourse.campus.data.model.AnnouncementsEntity
import woowacourse.campus.data.remote.response.AnnouncementDetailResponse
import woowacourse.campus.data.remote.response.AnnouncementResponse
import woowacourse.campus.data.remote.response.AnnouncementsResponse

object AnnouncementDataMapper {

    fun AnnouncementsResponse.toEntity() = AnnouncementsEntity(
        announcements = announcements.map { it.toEntity() },
        hasNext = hasNext,
        lastCursorId = lastCursorId,
    )

    fun AnnouncementResponse.toEntity() = AnnouncementEntity(
        id = id.toLong(),
        title = title,
        author = author,
        createdAt = createdAt,
        content = content,
    )

    fun AnnouncementDetailResponse.toEntity() = AnnouncementDetailEntity(
        title = title,
        author = author,
        createdAt = createdAt,
        content = content,
    )
}
