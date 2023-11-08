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
        startDestination = "로그인",
    ) {
        composable("로그인") {
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
        composable("announcementBoard") { // TODO: route string으로 관리
            AnnouncementBoardScreen { announcementId ->
                navController.navigateToAnnouncementDetail(announcementId)
            }
        }
        composable(
            route = "announcementDetail/{announcementId}",
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
