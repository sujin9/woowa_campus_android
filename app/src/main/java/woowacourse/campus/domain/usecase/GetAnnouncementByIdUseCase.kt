package woowacourse.campus.domain.usecase

import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.domain.model.AnnouncementDetail

class GetAnnouncementByIdUseCase(
    private val announcementRepository: AnnouncementRepository,
) {
    suspend operator fun invoke(announcementId: Long): AnnouncementDetail {
        return announcementRepository.getAnnouncementById(announcementId).toDomain()
    }
}
