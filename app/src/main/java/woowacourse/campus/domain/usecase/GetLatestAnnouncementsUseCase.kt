package woowacourse.campus.domain.usecase

import woowacourse.campus.data.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.model.AnnouncementPage

class GetLatestAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    operator fun invoke(): List<AnnouncementPage> {
        announcementRepository.getAnnouncements(0, 3).let { announcements ->
            return announcements.announcements
                .take(3)
                .map {
                    it.toDomain()
                }
        }
    }
}


