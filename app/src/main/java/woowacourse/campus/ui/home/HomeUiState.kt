package woowacourse.campus.ui.home

import woowacourse.campus.domain.model.Announcement

sealed interface HomeUiState {
    data class Success(
        val latestAnnouncements: List<Announcement>,
    ) : HomeUiState

    data object Loading : HomeUiState
}
