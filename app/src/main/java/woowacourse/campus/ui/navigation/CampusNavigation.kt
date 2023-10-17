package woowacourse.campus.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import woowacourse.campus.R
import woowacourse.campus.ui.home.HomeScreen
import woowacourse.campus.ui.mypage.MyPageScreen

@Composable
internal fun BottomNavigationView(navController: NavHostController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.MyPage)

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Green
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
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
                            .height(20.dp)
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = false,
            )
        }
    }
}


@Composable
internal fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.MyPage.screenRoute) {
            MyPageScreen()
        }
    }
}

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String,
) {
    data object Home : BottomNavItem(R.string.bottom_navigation_home, R.drawable.ic_home, "Home")
    data object MyPage :
        BottomNavItem(R.string.bottom_navigation_mypage, R.drawable.ic_my_page, "MyPage")
}