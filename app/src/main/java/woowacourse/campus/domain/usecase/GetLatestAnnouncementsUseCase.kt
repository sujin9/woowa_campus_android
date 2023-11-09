package woowacourse.campus.domain.usecase

import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.domain.model.Announcement

class GetLatestAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    suspend operator fun invoke(): List<Announcement> {
        announcementRepository.getAnnouncements(null, HOME_ANNOUNCEMENTS_SIZE)
            .let { announcements ->
                return announcements.announcements
                    .map {
                        it.toDomain()
                    }
            }
    }

    companion object {
        private const val HOME_ANNOUNCEMENTS_SIZE = 3
    }
}
