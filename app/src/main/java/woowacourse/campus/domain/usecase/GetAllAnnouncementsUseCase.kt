package woowacourse.campus.domain.usecase

import woowacourse.campus.data.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.model.AnnouncementPage

class GetAllAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    private var lastItemId = 0L
    private val size: Int
        get() = if (lastItemId == 0L) 20 else 10

    operator fun invoke(): List<AnnouncementPage> {
        val result = announcementRepository.getAnnouncements(cursor = lastItemId, size = size)
        lastItemId = result.announcements.last().id
        return result.announcements.map { it.toDomain() }
    }
}
