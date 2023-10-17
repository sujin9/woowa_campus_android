package woowacourse.campus.domain.usecase

import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.model.Announcement

class GetLatestAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    operator fun invoke(): List<Announcement> {
        announcementRepository.getAnnouncements(0).let { announcements ->
            return announcements.take(3)
        }
    }
}