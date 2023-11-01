package woowacourse.campus.ui.home

import woowacourse.campus.domain.model.AnnouncementPage


sealed interface HomeUiState {
    data class Success(
        val latestAnnouncements: List<AnnouncementPage>,
    ) : HomeUiState {
        val latestAnnouncementsSize get() = latestAnnouncements.size
    }

    data object Loading : HomeUiState
}
