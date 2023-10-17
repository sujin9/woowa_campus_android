package woowacourse.campus.ui.home

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.model.Announcement
import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase
import java.time.LocalDateTime

class HomeViewModelTest : BehaviorSpec({
    lateinit var viewModel: HomeViewModel
    val repository: AnnouncementRepository = mockk()

    beforeEach {
        viewModel = HomeViewModel(GetLatestAnnouncementsUseCase(repository))
    }

    Given("사용자가 앱을 실행했을 때") {
        every { repository.getAnnouncements(0) } returns fakeAnnouncements

        Then("공지사항 미리보기가 최대 3개 보인다") {
            val actual = viewModel.uiState.latestAnnouncementsSize
            actual shouldBe 3
        }
    }
})

private val fakeAnnouncements = List(5) { index ->
    Announcement(
        id = index.toLong(),
        title = "하티",
        content = "산군",
        createdAt = LocalDateTime.of(2021, 8, 1, 0, 0, 0)
    )
}
