package woowacourse.campus.ui.announcement.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import woowacourse.campus.domain.model.AnnouncementPage
import woowacourse.campus.domain.usecase.GetAllAnnouncementsUseCase

class AnnouncementBoardViewModel(
    private val getAllAnnouncementsUseCase: GetAllAnnouncementsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AnnouncementBoardUiState> =
        MutableStateFlow(AnnouncementBoardUiState.Loading)
    val uiState: StateFlow<AnnouncementBoardUiState> get() = _uiState.asStateFlow()

    fun getMoreAnnouncements() {
        viewModelScope.launch {
            runCatching {
                getAllAnnouncementsUseCase()
            }.onSuccess { announcementPages ->
                when (uiState.value) {
                    is AnnouncementBoardUiState.Loading -> {
                        _uiState.value = AnnouncementBoardUiState.Success(announcementPages)
                    }

                    is AnnouncementBoardUiState.Success -> {
                        val success = uiState.value as AnnouncementBoardUiState.Success

                        _uiState.value =
                            success.copy(announcements = success.announcements + announcementPages)
                    }
                }
            }
        }
    }
}

sealed interface AnnouncementBoardUiState {
    data class Success(
        val announcements: List<AnnouncementPage>,
    ) : AnnouncementBoardUiState

    data object Loading : AnnouncementBoardUiState
}
