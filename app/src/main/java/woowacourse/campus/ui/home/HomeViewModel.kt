package woowacourse.campus.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import woowacourse.campus.data.remote.api.GetAllAnnouncementApi
import woowacourse.campus.data.remote.api.GetAnnouncementApi
import woowacourse.campus.data.repository.AnnouncementRepository
import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase

class HomeViewModel(
    getLatestAnnouncementsUseCase: GetLatestAnnouncementsUseCase,
) : ViewModel() {
    val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    init {
        viewModelScope.launch {
            uiState.value = HomeUiState.Success(
                latestAnnouncements = getLatestAnnouncementsUseCase(),
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    getLatestAnnouncementsUseCase = GetLatestAnnouncementsUseCase(
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
