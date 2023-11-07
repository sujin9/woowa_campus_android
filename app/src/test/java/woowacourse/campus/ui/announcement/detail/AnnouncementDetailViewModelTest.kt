package woowacourse.campus.ui.announcement.detail

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import woowacourse.campus.data.model.AnnouncementDetailEntity
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.mapper.AnnouncementDomainMapper.toDomain
import woowacourse.campus.domain.usecase.GetAnnouncementByIdUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class AnnouncementDetailViewModelTest : StringSpec({
    lateinit var viewModel: AnnouncementDetailViewModel
    val repository: AnnouncementRepository = mockk()

    Dispatchers.setMain(Dispatchers.Unconfined)
    coroutineTestScope = true

    beforeEach {
        viewModel = AnnouncementDetailViewModel(GetAnnouncementByIdUseCase(repository))
    }

    "아이디가 0인 게시글을 조회하면 해당하는 공지사항의 상세 정보를 보여준다" {
        coEvery { repository.getAnnouncementById(0L) } returns fakeAnnouncementDetailEntity

        viewModel.updateUiState(0L)

        val state = viewModel.uiState.value as AnnouncementDetailUiState.Success
        val actual = state.announcement
        val expected = fakeAnnouncementDetailEntity.toDomain()
        actual shouldBe expected
    }
}) {
    companion object {
        private val fakeAnnouncementDetailEntity = AnnouncementDetailEntity(
            author = "레아",
            content = "공지사항 내용입니당",
            createdAt = "2021-08-20T14:00:00",
            title = "공지 제목",
        )
    }
}
