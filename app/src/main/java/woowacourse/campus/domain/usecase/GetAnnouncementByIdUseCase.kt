package woowacourse.campus.domain.usecase

import woowacourse.campus.data.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.model.Announcement

class GetAnnouncementByIdUseCase(
    private val announcementRepository: AnnouncementRepository
) {

    operator fun invoke(announcementId: Long): Announcement {
        return announcementRepository.getAnnouncementById(announcementId).toDomain()
    }
}