package woowacourse.campus.data.mapper

import woowacourse.campus.data.model.AnnouncementEntity
import woowacourse.campus.data.model.AnnouncementPageEntity
import woowacourse.campus.domain.model.Announcement
import woowacourse.campus.domain.model.AnnouncementPage

object AnnouncementDomainMapper {

    fun AnnouncementPageEntity.toDomain() = AnnouncementPage(
        id = id,
        title = title,
        author = author,
        createdAt = createdAt,
    )

    fun AnnouncementEntity.toDomain() = Announcement(
        id = id,
        title = title,
        content = content,
        author = author,
        createdAt = createdAt
    )
}