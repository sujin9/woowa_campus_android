package woowacourse.campus.ui.home

import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase

class HomeViewModel(
    getLatestAnnouncementsUseCase: GetLatestAnnouncementsUseCase,
) {
    val uiState: HomeUiState

    init {
        uiState = HomeUiState(
            latestAnnouncements = getLatestAnnouncementsUseCase()
        )
    }
}
