package woowacourse.campus.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import woowacourse.campus.WoowaApplication
import woowacourse.campus.data.repository.AuthRepository

class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    val loginState: StateFlow<Boolean> =
        MutableStateFlow(authRepository.getToken(TOKEN).isEmpty()).asStateFlow()

    fun updateToken(password: String) {
        authRepository.putToken(TOKEN, password)
    }

    companion object {
        private const val TOKEN = "TOKEN"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    authRepository = AuthRepository(
                        WoowaApplication.getApplicationContext(),
                    ),
                )
            }
        }
    }
}
