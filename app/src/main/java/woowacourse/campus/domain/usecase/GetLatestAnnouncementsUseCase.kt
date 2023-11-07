package woowacourse.campus.domain.usecase

import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.domain.model.Announcement

class GetLatestAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    suspend operator fun invoke(): List<Announcement> {
        announcementRepository.getAnnouncements(null, 3).let { announcements ->
            return announcements.announcements
                .map {
                    it.toDomain()
                }
        }
    }
}
