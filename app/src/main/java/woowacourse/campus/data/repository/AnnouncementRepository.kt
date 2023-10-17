package woowacourse.campus.data.repository

import api.AnnouncementApi
import model.AnnouncementResponse
import model.AnnouncementsByPageResponse

class AnnouncementRepository(
    private val api: AnnouncementApi,
) {
    suspend fun getAnnouncements(page: Int): AnnouncementsByPageEntity {
        return api.findAllAnnouncementByPage(page, 10, "password")
            .body()
            .toEntity()
    }
}

data class AnnouncementsByPageEntity(
    val announcements: List<AnnouncementEntity>,
    val page: Int,
    val propertySize: Int,
    val totalElements: Int,
    val totalPages: Int,
)

data class AnnouncementEntity(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: String,
)

fun AnnouncementsByPageResponse.toEntity() = AnnouncementsByPageEntity(
    announcements = announcements.map { it.toEntity() },
    page = page,
    propertySize = propertySize,
    totalElements = totalElements,
    totalPages = totalPages,
)

fun AnnouncementResponse.toEntity() = AnnouncementEntity(
    id = id.toLong(),
    title = title,
    content = content,
    author = author,
    createdAt = createdAt,
)
