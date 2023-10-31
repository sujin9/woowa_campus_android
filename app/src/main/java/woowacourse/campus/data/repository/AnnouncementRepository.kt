package woowacourse.campus.data.repository

import api.GetAllAnnouncementApi
import model.AnnouncementPageResponse
import model.AnnouncementsByPageResponse
import woowacourse.campus.data.mapper.AnnouncementDataMapper.toEntity
import woowacourse.campus.data.model.AnnouncementsByPageEntity

class AnnouncementRepository(
    private val api: GetAllAnnouncementApi,
) {

    fun getAnnouncements(page: Int): AnnouncementsByPageEntity {
        return getFixture(page, 10, "password")
            .toEntity()
    }

    companion object {

        fun getFixture(
            page: Int,
            size: Int = 10,
            authorization: String = "password"
        ): AnnouncementsByPageResponse {

            return pageFixture.firstOrNull {
                it.page == page
            } ?: AnnouncementsByPageResponse(
                announcements = listOf(),
                page = 0,
                propertySize = 0,
                totalElements = 0,
                totalPages = 0
            )
        }

        private val announcementFixture: AnnouncementPageResponse = AnnouncementPageResponse(
            id = 0, title = "6기 공지사항", author = "하티", createdAt = "11.22 15:33:21"
        )

        private val pageFixture: List<AnnouncementsByPageResponse> = listOf(
            AnnouncementsByPageResponse(
                announcements = List(10) { announcementFixture },
                page = 0,
                propertySize = 0,
                totalElements = 0,
                totalPages = 0
            ),
            AnnouncementsByPageResponse(
                announcements = List(10) { announcementFixture },
                page = 1,
                propertySize = 0,
                totalElements = 0,
                totalPages = 0
            ),
            AnnouncementsByPageResponse(
                announcements = List(10) { announcementFixture },
                page = 2,
                propertySize = 0,
                totalElements = 0,
                totalPages = 0
            )
        )
    }
}
