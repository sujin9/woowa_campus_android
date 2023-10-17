package woowacourse.campus.domain.usecase

import woowacourse.campus.data.repository.AnnouncementEntity
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.model.Announcement
import java.time.LocalDateTime

class GetLatestAnnouncementsUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    suspend operator fun invoke(): List<Announcement> {
        announcementRepository.getAnnouncements(0).let { announcements ->
            return announcements.announcements
                .take(3)
                .map {
                    it.toDomain()
                }
        }
    }
}

fun AnnouncementEntity.toDomain() = Announcement(
    id = id,
    title = title,
    content = content,
    createdAt = LocalDateTime.parse(createdAt),
)
