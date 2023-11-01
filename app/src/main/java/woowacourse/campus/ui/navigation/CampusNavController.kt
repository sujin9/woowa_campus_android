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

    fun popBackStack() {
        navController.popBackStack()
    }

    fun navigateToAnnouncementBoard() {
        navController.navigate("공지사항")
    }

    fun navigateToAnnouncementDetail(announcementId: Long) {
        navController.navigate("상세보기/$announcementId")
    }

    @Composable
    fun isBottomBarVisible() = when (currentDestination?.route) {
        BottomNavItem.Home.screenRoute, BottomNavItem.MyPage.screenRoute -> true
        else -> false
    }

    @Composable
    fun isTopAppBarVisible() = when (currentDestination?.route) {
        BottomNavItem.Home.screenRoute, BottomNavItem.MyPage.screenRoute -> false
        else -> true
    }
}
