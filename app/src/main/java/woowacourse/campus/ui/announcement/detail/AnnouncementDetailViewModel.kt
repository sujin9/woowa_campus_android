package woowacourse.campus.ui.announcement.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import woowacourse.campus.domain.usecase.GetAnnouncementByIdUseCase

class AnnouncementDetailViewModel(
    private val getAnnouncementByIdUseCase: GetAnnouncementByIdUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<AnnouncementDetailUiState> =
        MutableStateFlow(AnnouncementDetailUiState.Loading)
    val uiState: StateFlow<AnnouncementDetailUiState> = _uiState.asStateFlow()

    fun updateUiState(announcementId: Long) {
        viewModelScope.launch {
            runCatching {
                getAnnouncementByIdUseCase(announcementId)
            }.onSuccess { announcement ->
                _uiState.value = AnnouncementDetailUiState.Success(
                    id = announcement.id,
                    title = announcement.title,
                    content = announcement.content,
                    author = announcement.author,
                    createdAt = announcement.createdAt,
                )
            }.onFailure { message ->
                _uiState.value = AnnouncementDetailUiState.Failure(message)
            }
        }
    }
}

sealed interface AnnouncementDetailUiState {
    data class Success(
        val id: Int,
        val title: String,
        val content: String,
        val author: String,
        val createdAt: String
    ) : AnnouncementDetailUiState

    data object Loading : AnnouncementDetailUiState
    data class Failure(
        val message: Throwable
    ) : AnnouncementDetailUiState
}