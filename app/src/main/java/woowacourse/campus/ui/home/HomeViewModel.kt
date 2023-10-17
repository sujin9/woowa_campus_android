package woowacourse.campus.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import woowacourse.campus.domain.usecase.GetLatestAnnouncementsUseCase

class HomeViewModel(
    getLatestAnnouncementsUseCase: GetLatestAnnouncementsUseCase,
) : ViewModel() {
    val uiState = MutableLiveData<HomeUiState>()

    init {
        viewModelScope.launch {
            uiState.value = HomeUiState(
                latestAnnouncements = getLatestAnnouncementsUseCase()
            )
        }
    }
}
