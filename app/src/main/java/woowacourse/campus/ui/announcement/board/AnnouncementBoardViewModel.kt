package woowacourse.campus.ui.announcement.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import woowacourse.campus.domain.model.AnnouncementPage
import woowacourse.campus.domain.usecase.GetAllAnnouncementsUseCase

class AnnouncementBoardViewModel(
    private val getAllAnnouncementsUseCase: GetAllAnnouncementsUseCase
) : ViewModel() {

    val uiState: StateFlow<AnnouncementBoardUiState> = flow {
        emit(getAllAnnouncementsUseCase())
    }.map {
        AnnouncementBoardUiState.Success(
            announcements = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = AnnouncementBoardUiState.Loading,
    )
}

sealed interface AnnouncementBoardUiState {
    data class Success(
        val announcements: List<AnnouncementPage>
    ) : AnnouncementBoardUiState

    data object Loading : AnnouncementBoardUiState
}