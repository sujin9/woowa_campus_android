package woowacourse.campus.domain.usecase

import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.domain.model.Announcement

class GetAllAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    private var lastItemId: Long? = null
    private var hasNext: Boolean = true
    private val size: Int
        get() = if (lastItemId == null) 20 else 10

    suspend operator fun invoke(): List<Announcement> {
        val result = announcementRepository.getAnnouncements(lastItemId = lastItemId, size = size)
        lastItemId = result.announcements.last().id
        hasNext = result.hasNext

        return result.announcements.map { it.toDomain() }
    }
}
