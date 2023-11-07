package woowacourse.campus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

class CampusNavController(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination
    val currentRoute: String? @Composable get() = currentDestination?.route

    fun popBackStack() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToAnnouncementBoard() {
        navController.navigate("announcementBoard")
    }

    fun navigateToAnnouncementDetail(announcementId: Long) {
        navController.navigate("announcementDetail/$announcementId")
    }

    fun navigateToHome() {
        navController.navigate(BottomNavItem.Home.screenRoute)
    }

    @Composable
    fun isBottomBarVisible() = when (currentRoute) {
        BottomNavItem.Home.screenRoute, BottomNavItem.MyPage.screenRoute -> true
        else -> false
    }

    @Composable
    fun isTopAppBarVisible() = when (currentRoute) {
        BottomNavItem.Home.screenRoute, BottomNavItem.MyPage.screenRoute, "로그인" -> false
        else -> true
    }
}
