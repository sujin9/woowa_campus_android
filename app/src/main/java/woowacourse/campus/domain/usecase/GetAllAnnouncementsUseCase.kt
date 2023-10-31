package woowacourse.campus.domain.usecase

import woowacourse.campus.data.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.model.AnnouncementPage

class GetAllAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    private var index = 0

    operator fun invoke(): List<AnnouncementPage> {
        announcementRepository.getAnnouncements(index).let { announcements ->
            return announcements.announcements
                .map {
                    it.toDomain()
                }
        }
    }
}
