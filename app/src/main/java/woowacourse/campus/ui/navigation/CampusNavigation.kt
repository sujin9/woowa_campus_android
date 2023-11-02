package woowacourse.campus.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import woowacourse.campus.R
import woowacourse.campus.ui.announcement.board.AnnouncementBoardScreen
import woowacourse.campus.ui.announcement.detail.AnnouncementDetailScreen
import woowacourse.campus.ui.home.HomeScreen
import woowacourse.campus.ui.login.LoginScreen
import woowacourse.campus.ui.myPage.MyPageScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopAppBarView(navController: CampusNavController) {
    val navBackStackEntry by navController.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    CenterAlignedTopAppBar(
        title = {
            currentRoute?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_previous),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        },
    )
}

@Composable
internal fun BottomNavigationView(navController: CampusNavController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.MyPage)

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Green,
    ) {
        val navBackStackEntry by navController.navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.screenRoute,
                onClick = {
                    // TODO: navController 로 옮기기
                    navController.navController.navigate(item.screenRoute) {
                        navController.navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp),
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = false,
            )
        }
    }
}

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
        composable("공지사항") { // TODO: string resource
            AnnouncementBoardScreen { announcementId ->
                navController.navigateToAnnouncementDetail(announcementId)
            }
        }
        composable(route = "상세보기/{announcementId}", arguments = listOf(
            navArgument("announcementId") {
                type = NavType.LongType
            }
        )) {
            val arg = it.arguments?.getLong("/{announcementId}") ?: 0
            AnnouncementDetailScreen(id = arg)
        }
    }
}

sealed class BottomNavItem(
    val title: Int,
    val icon: Int,
    val screenRoute: String,
) {
    data object Home : BottomNavItem(R.string.bottom_navigation_home, R.drawable.ic_home, "Home")
    data object MyPage :
        BottomNavItem(R.string.bottom_navigation_my_page, R.drawable.ic_my_page, "MyPage")
}
