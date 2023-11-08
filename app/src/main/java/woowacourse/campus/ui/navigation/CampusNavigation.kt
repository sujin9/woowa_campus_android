package woowacourse.campus.ui.navigation

import androidx.compose.foundation.layout.size
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
    val title = stringResource(
        id = when {
            currentRoute == "announcementBoard" -> R.string.top_bar_announcement_baord
            currentRoute?.contains("announcementDetail") == true -> R.string.top_bar_announcement_detail
            else -> R.string.app_name
        },
    )
    CenterAlignedTopAppBar(
        title = {
            currentRoute?.let {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_previous),
                    contentDescription = stringResource(R.string.top_bar_back_image_description),
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
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = navController.currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier.size(20.dp),
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
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

sealed class BottomNavItem(
    val title: Int,
    val icon: Int,
    val screenRoute: String,
) {
    data object Home : BottomNavItem(R.string.bottom_navigation_home, R.drawable.ic_home, "Home")
    data object MyPage :
        BottomNavItem(R.string.bottom_navigation_my_page, R.drawable.ic_my_page, "MyPage")
}
