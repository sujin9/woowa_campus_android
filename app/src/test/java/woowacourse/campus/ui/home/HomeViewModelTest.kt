package woowacourse.campus.ui.home

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import woowacourse.campus.data.model.AnnouncementEntity
import woowacourse.campus.data.model.AnnouncementsEntity
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BehaviorSpec({
    lateinit var viewModel: HomeViewModel
    val repository: AnnouncementRepository = mockk()

    Dispatchers.setMain(Dispatchers.Unconfined)

    beforeEach {
        viewModel = HomeViewModel(GetLatestAnnouncementsUseCase(repository))
    }

    Given("사용자가 앱을 실행했을 때") {
        coEvery { repository.getAnnouncements(null, 3) } returns fakeAnnouncementEntity

        Then("공지사항 미리보기가 최대 3개 보인다") {
            val state = viewModel.uiState.value as HomeUiState.Success
            val actual = state.latestAnnouncements
            actual shouldHaveSize 3
        }
    }
})

private val fakeAnnouncements = List(3) { index ->
    AnnouncementEntity(
        id = index.toLong(),
        title = "하티 $index",
        content = "산군 $index",
        author = "레아",
        createdAt = "2021-08-20T14:00:00",
    )
}

private val fakeAnnouncementEntity = AnnouncementsEntity(
    announcements = fakeAnnouncements,
    hasNext = true,
    lastCursorId = 2,
)
