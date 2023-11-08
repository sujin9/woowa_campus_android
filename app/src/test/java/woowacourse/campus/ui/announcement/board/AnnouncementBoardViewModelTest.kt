package woowacourse.campus.ui.announcement.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import woowacourse.campus.data.model.AnnouncementEntity
import woowacourse.campus.data.model.AnnouncementsEntity
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.usecase.GetAllAnnouncementsUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class AnnouncementBoardViewModelTest : StringSpec({
    lateinit var announcementViewModel: AnnouncementBoardViewModel
    val repository: AnnouncementRepository = mockk()

    Dispatchers.setMain(Dispatchers.Unconfined)

    beforeEach {
        announcementViewModel = AnnouncementBoardViewModel(GetAllAnnouncementsUseCase(repository))
        coEvery { repository.getAnnouncements(null, 10) } returns fakeAnnouncementEntity_0to9
    }

    "처음 공지목록을 불러올 때, 공지 10개를 불러온다" {
        announcementViewModel.getMoreAnnouncements()

        val actual = announcementViewModel.uiState.value as AnnouncementBoardUiState.Success
        actual.announcements shouldHaveSize 10
    }

    "추가 공지를 요청하면, 공지 5개를 불러와 총 15개의 공지를 보여준다" {
        coEvery { repository.getAnnouncements(9, 5) } returns fakeAnnouncementEntity_10to14

        announcementViewModel.getMoreAnnouncements()
        announcementViewModel.getMoreAnnouncements()

        val actual = announcementViewModel.uiState.value as AnnouncementBoardUiState.Success
        actual.announcements shouldHaveSize 15
    }

    "더 이상 공지가 없는 경우, 추가 공지를 요청하면, 빈 리스트를 불러와 총 15개의 공지를 보여준다" {
        coEvery { repository.getAnnouncements(9, 5) } returns fakeAnnouncementEntity_10to14

        announcementViewModel.getMoreAnnouncements()
        announcementViewModel.getMoreAnnouncements()
        announcementViewModel.getMoreAnnouncements()

        val actual = announcementViewModel.uiState.value as AnnouncementBoardUiState.Success
        actual.announcements shouldHaveSize 15
    }
}) {
    companion object {
        private val fakeAnnouncements = List(15) { index ->
            AnnouncementEntity(
                id = index.toLong(),
                title = "하티 $index",
                content = "산군 $index",
                author = "레아",
                createdAt = "2021-08-20T14:00:00",
            )
        }

        private val fakeAnnouncementEntity_0to9 = AnnouncementsEntity(
            announcements = fakeAnnouncements.subList(0, 10),
            hasNext = true,
            lastCursorId = 9,
        )

        private val fakeAnnouncementEntity_10to14 = AnnouncementsEntity(
            announcements = fakeAnnouncements.subList(10, 15),
            hasNext = false,
            lastCursorId = 14,
        )
    }
}


