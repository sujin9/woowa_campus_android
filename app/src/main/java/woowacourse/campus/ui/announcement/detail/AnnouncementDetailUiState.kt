package woowacourse.campus.ui.announcement.detail

import woowacourse.campus.domain.model.AnnouncementDetail

sealed interface AnnouncementDetailUiState {
    data class Success(
        val announcement: AnnouncementDetail,
    ) : AnnouncementDetailUiState

    data object Loading : AnnouncementDetailUiState
    data class Failure(
        val message: Throwable,
    ) : AnnouncementDetailUiState
}
