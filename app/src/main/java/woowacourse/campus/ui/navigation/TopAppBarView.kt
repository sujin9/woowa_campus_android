package woowacourse.campus.ui.navigation

import androidx.compose.material.Icon
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import woowacourse.campus.R

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
