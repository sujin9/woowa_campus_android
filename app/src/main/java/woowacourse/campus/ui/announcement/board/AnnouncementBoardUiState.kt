package woowacourse.campus.ui.announcement.board

import woowacourse.campus.domain.model.Announcement

sealed interface AnnouncementBoardUiState {
    data class Success(
        val announcements: List<Announcement>,
    ) : AnnouncementBoardUiState

    data object Loading : AnnouncementBoardUiState
}
