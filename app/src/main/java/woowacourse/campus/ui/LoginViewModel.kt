package woowacourse.campus.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import woowacourse.campus.data.repository.AuthRepository

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    val loginState: StateFlow<Boolean> =
        MutableStateFlow(authRepository.getToken(TOKEN).isEmpty()).asStateFlow()

    fun updateToken(password: String) {
        authRepository.putToken(TOKEN, password)
    }

    companion object {
        private const val TOKEN = "TOKEN"
    }
}