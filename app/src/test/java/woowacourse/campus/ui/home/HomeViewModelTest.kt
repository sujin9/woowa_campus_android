package woowacourse.campus.ui.home

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import woowacourse.campus.InstantExecutorListener
import woowacourse.campus.data.repository.AnnouncementEntity
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.data.repository.AnnouncementsByPageEntity
import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase
import woowacourse.campus.getOrAwaitValue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BehaviorSpec({
    lateinit var viewModel: HomeViewModel
    val repository: AnnouncementRepository = mockk()

    Dispatchers.setMain(Dispatchers.Unconfined)
    listener(InstantExecutorListener())

    beforeEach {
        viewModel = HomeViewModel(GetLatestAnnouncementsUseCase(repository))
    }

    Given("사용자가 앱을 실행했을 때") {
        coEvery { repository.getAnnouncements(0) } returns fakeAnnouncementEntity

        Then("공지사항 미리보기가 최대 3개 보인다") {
            val actual = viewModel.uiState.getOrAwaitValue().latestAnnouncementsSize
            actual shouldBe 3
        }
    }
})

private val fakeAnnouncements = List(5) { index ->
    AnnouncementEntity(
        id = index.toLong(),
        title = "하티",
        content = "산군",
        author = "레아",
        createdAt = "2021-08-20T14:00:00"
    )
}

private val fakeAnnouncementEntity = AnnouncementsByPageEntity(
    announcements = fakeAnnouncements,
    page = 0,
    propertySize = 10,
    totalElements = 5,
    totalPages = 1,
)
