package woowacourse.campus.domain.mapper

import woowacourse.campus.data.model.AnnouncementDetailEntity
import woowacourse.campus.data.model.AnnouncementEntity
import woowacourse.campus.domain.model.Announcement
import woowacourse.campus.domain.model.AnnouncementDetail

object AnnouncementDomainMapper {

    fun AnnouncementDetailEntity.toDomain() = AnnouncementDetail(
        title = title,
        author = author,
        createdAt = createdAt,
        content = content,
    )

    fun AnnouncementEntity.toDomain() = Announcement(
        id = id,
        title = title,
        content = content,
        author = author,
        createdAt = createdAt,
    )
}
