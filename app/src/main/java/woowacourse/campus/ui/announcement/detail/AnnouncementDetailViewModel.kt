package woowacourse.campus.ui.announcement.detail

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
import woowacourse.campus.domain.usecase.GetAnnouncementByIdUseCase

class AnnouncementDetailViewModel(
    private val getAnnouncementByIdUseCase: GetAnnouncementByIdUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AnnouncementDetailUiState> =
        MutableStateFlow(AnnouncementDetailUiState.Loading)
    val uiState: StateFlow<AnnouncementDetailUiState> get() = _uiState.asStateFlow()

    fun updateUiState(announcementId: Long) {
        viewModelScope.launch {
            runCatching {
                getAnnouncementByIdUseCase(announcementId)
            }.onSuccess { announcement ->
                _uiState.value = AnnouncementDetailUiState.Success(
                    announcement = announcement,
                )
            }.onFailure { message ->
                _uiState.value = AnnouncementDetailUiState.Failure(message)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AnnouncementDetailViewModel(
                    getAnnouncementByIdUseCase = GetAnnouncementByIdUseCase(
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
