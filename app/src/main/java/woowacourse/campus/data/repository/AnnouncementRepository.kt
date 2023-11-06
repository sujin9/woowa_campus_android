package woowacourse.campus.data.repository

import woowacourse.campus.data.mapper.AnnouncementDataMapper.toEntity
import woowacourse.campus.data.model.AnnouncementDetailEntity
import woowacourse.campus.data.model.AnnouncementsEntity
import woowacourse.campus.data.remote.api.GetAllAnnouncementApi
import woowacourse.campus.data.remote.api.GetAnnouncementApi

class AnnouncementRepository(
    private val getAllAnnouncementApi: GetAllAnnouncementApi,
    private val getAnnouncementApi: GetAnnouncementApi,
) {
    suspend fun getAnnouncements(lastItemId: Long?, size: Int): AnnouncementsEntity {
        return getAllAnnouncementApi.getAnnouncements(lastItemId, size).toEntity()
    }

    suspend fun getAnnouncementById(announcementId: Long): AnnouncementDetailEntity {
        return getAnnouncementApi.getAnnouncement(announcementId).toEntity()
    }
}
