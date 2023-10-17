package woowacourse.campus.domain.usecase

import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.model.Announcement

class GetAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    operator fun invoke(page: Int): List<Announcement> {
        return announcementRepository.getAnnouncements(page)
    }
}