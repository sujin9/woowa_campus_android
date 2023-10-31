package woowacourse.campus.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase

class HomeViewModel(
    getLatestAnnouncementsUseCase: GetLatestAnnouncementsUseCase,
) : ViewModel() {
    val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    init {
        viewModelScope.launch {
            uiState.value = HomeUiState.Success(
                latestAnnouncements = getLatestAnnouncementsUseCase()
            )
        }
    }
}
