package woowacourse.campus.domain.usecase

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

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllAnnouncementsUseCaseTest : BehaviorSpec({
    val repository: AnnouncementRepository = mockk()
    val useCase = GetAllAnnouncementsUseCase(repository)

    Dispatchers.setMain(Dispatchers.Unconfined)

    Given("공지사항 목록을 확인하려고 할 때") {
        coEvery { repository.getAnnouncements(null, 10) } returns fakeAnnouncementEntity_0to9
        coEvery { repository.getAnnouncements(9, 5) } returns fakeAnnouncementEntity_10to14

        When("처음 공지목록을 불러올 때") {
            Then("공지 10개를 불러온다") {
                val actual = useCase()
                actual shouldHaveSize 10
            }
        }

        When("그 다음 공지목록을 불러올 때") {
            Then("공지 5개를 추가로 불러온다") {
                val actual = useCase()
                actual shouldHaveSize 5
            }
        }

        When("그 다음 공지사항이 없을 때") {
            Then("빈 목록을 불러온다") {
                val actual = useCase()
                actual shouldHaveSize 0
            }
        }
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
