package woowacourse.campus.ui.announcement.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import woowacourse.campus.data.remote.api.GetAllAnnouncementApi
import woowacourse.campus.data.remote.api.GetAnnouncementApi
import woowacourse.campus.data.repository.AnnouncementRepository
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
                        val success = AnnouncementBoardUiState.Success(announcementPages)
                        _uiState.value = success
                    }

                    is AnnouncementBoardUiState.Success -> {
                        val success = uiState.value as AnnouncementBoardUiState.Success
                        _uiState.value =
                            success.copy(announcements = success.announcements + announcementPages)
                    }
                }
            }.onFailure {}
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AnnouncementBoardViewModel(
                    getAllAnnouncementsUseCase = GetAllAnnouncementsUseCase(
                        announcementRepository = AnnouncementRepository(
                            GetAllAnnouncementApi(),
                            GetAnnouncementApi(),
                        ),
                    ),
                )
            }
        }
    }
}
