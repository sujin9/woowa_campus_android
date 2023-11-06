package woowacourse.campus.domain.usecase

import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.domain.model.Announcement

class GetAllAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    private var lastItemId = 0L
    private val size: Int
        get() = if (lastItemId == 0L) 20 else 10

    suspend operator fun invoke(): List<Announcement> {
        val result = announcementRepository.getAnnouncements(lastItemId = lastItemId, size = size)
        lastItemId = result.announcements.last().id
        return result.announcements.map { it.toDomain() }
    }
}
