package woowacourse.campus

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.campus.ui.navigation.BottomNavItem
import woowacourse.campus.ui.navigation.CampusNavController

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreenView(CampusNavController(navController))
        }
    }

    @Test
    fun 앱을_실행하면_시작_화면은_로그인_화면이다() {
        val actual = navController.currentBackStackEntry?.destination?.route
        val expected = "로그인"
        assertThat(actual).isEqualTo(expected)
    }
}