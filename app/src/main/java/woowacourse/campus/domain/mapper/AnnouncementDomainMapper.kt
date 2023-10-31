package woowacourse.campus.data.mapper

import woowacourse.campus.data.model.AnnouncementPageEntity
import woowacourse.campus.domain.model.AnnouncementPage
import java.time.LocalDateTime

object AnnouncementDomainMapper {

    fun AnnouncementPageEntity.toDomain() = AnnouncementPage(
        id = id,
        title = title,
        createdAt = LocalDateTime.parse(createdAt),
    )
}