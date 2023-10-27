package woowacourse.campus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

class CampusNavController(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun navigateToAnnouncementBoard() {
        navController.navigate("AnnouncementBoard")
    }

    fun navigateToAnnouncementDetail() {
        navController.navigate("AnnouncementDetail")
    }

    @Composable
    fun isBottomBarVisible() = when (currentDestination?.route) {
        BottomNavItem.Home.screenRoute, BottomNavItem.MyPage.screenRoute -> true
        else -> false
    }
}
