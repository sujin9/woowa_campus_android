package woowacourse.campus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import woowacourse.campus.ui.announcement.board.AnnouncementBoardScreen
import woowacourse.campus.ui.announcement.detail.AnnouncementDetailScreen
import woowacourse.campus.ui.home.HomeScreen
import woowacourse.campus.ui.login.LoginScreen
import woowacourse.campus.ui.myPage.MyPageScreen

@Composable
internal fun NavigationGraph(navController: CampusNavController) {
    NavHost(
        navController = navController.navController,
        startDestination = ScreenRoute.LOGIN.route,
    ) {
        composable(ScreenRoute.LOGIN.route) {
            LoginScreen {
                navController.navigateToHome()
            }
        }
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(
                onAnnouncementBoardClick = { navController.navigateToAnnouncementBoard() },
                onAnnouncementItemClick = { announcementId ->
                    navController.navigateToAnnouncementDetail(announcementId)
                },
            )
        }
        composable(BottomNavItem.MyPage.screenRoute) {
            MyPageScreen()
        }
        composable(ScreenRoute.ANNOUNCEMENT_BOARD.route) {
            AnnouncementBoardScreen { announcementId ->
                navController.navigateToAnnouncementDetail(announcementId)
            }
        }
        composable(
            route = "${ScreenRoute.ANNOUNCEMENT_DETAIL.route}/{announcementId}",
            arguments = listOf(
                navArgument("announcementId") {
                    type = NavType.LongType
                },
            ),
        ) {
            val arg = it.arguments?.getLong("/{announcementId}") ?: 0
            AnnouncementDetailScreen(id = arg)
        }
    }
}
