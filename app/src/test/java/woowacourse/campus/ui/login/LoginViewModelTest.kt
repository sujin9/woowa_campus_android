package woowacourse.campus.ui.login

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import woowacourse.campus.data.repository.AuthRepository

class LoginViewModelTest : StringSpec({
    lateinit var viewModel: LoginViewModel
    lateinit var authRepository: AuthRepository

    beforeEach {
        authRepository = mockk(relaxed = true)
        every { authRepository.getToken(any()) } returns ""
        viewModel = LoginViewModel(authRepository)
    }

    "최초 생성시 사용자 토큰 정보는 빈 값이다" {
        viewModel.loginState.value shouldBe false
    }

    "비밀번호가 입력되면, loginState가 true로 설정된다" {
        val input = "1234"
        every { authRepository.getToken(any()) } returns input

        viewModel.updateToken(input)

        viewModel.loginState.value shouldBe true
    }
})