package woowacourse.campus.data.repository

import api.GetAllAnnouncementApi
import api.GetAnnouncementApi
import model.AnnouncementPageResponse
import model.AnnouncementResponse
import model.AnnouncementsByPageResponse
import woowacourse.campus.data.mapper.AnnouncementDataMapper.toEntity
import woowacourse.campus.data.model.AnnouncementEntity
import woowacourse.campus.data.model.AnnouncementsByPageEntity

class AnnouncementRepository(
    private val getAllAnnouncementApi: GetAllAnnouncementApi,
    private val getAnnouncementApi: GetAnnouncementApi,
) {
    fun getAnnouncements(cursor: Long, size: Int): AnnouncementsByPageEntity {
        return getFixture(cursor, size, "password")
            .toEntity()
    }

    fun getAnnouncementById(announcementId: Long): AnnouncementEntity {
        return getAnnouncement(announcementId, "password").toEntity()
    }

    companion object {

        fun getAnnouncement(
            id: Long,
            authorization: String = "",
        ) = announcementDetailFixture(id.toInt())

        fun getFixture(
            cursor: Long,
            size: Int,
            authorization: String = "password",
        ): AnnouncementsByPageResponse {
            return pageFixture.firstOrNull {
                it.page == cursor.toInt()
            } ?: AnnouncementsByPageResponse(
                announcements = listOf(),
                page = 0,
                propertySize = 0,
                totalElements = 0,
                totalPages = 0,
            )
        }

        private fun announcementDetailFixture(id: Int): AnnouncementResponse = AnnouncementResponse(
            id = id,
            title = "6기 공지사항입니다. $id",
            content = "테스트입니다",
            author = "하티",
            createdAt = "11.22 15:33:21",
        )

        private fun announcementFixture(id: Int): AnnouncementPageResponse =
            AnnouncementPageResponse(
                id = id,
                title = "6기 공지사항 $id",
                author = "하티",
                createdAt = "11.22 15:33:21",
            )

        private val pageFixture: List<AnnouncementsByPageResponse> = List(100) { cursor ->
            AnnouncementsByPageResponse(
                announcements = List(10) { announcementFixture(cursor + it) },
                page = cursor,
                propertySize = 0,
                totalElements = 0,
                totalPages = 0,
            )
        }
    }
}
