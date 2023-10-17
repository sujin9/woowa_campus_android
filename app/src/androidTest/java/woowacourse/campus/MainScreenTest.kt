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

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreenView(navController)
        }
    }

    @Test
    fun 앱을_실행하면_시작_화면은_홈이다() {
        val actual = navController.currentBackStackEntry?.destination?.route
        val expected = BottomNavItem.Home.screenRoute
        assertThat(actual).isEqualTo(expected)
    }
}