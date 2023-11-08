package woowacourse.campus.ui.navigation

import androidx.compose.material.Icon
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import woowacourse.campus.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopAppBarView(navController: CampusNavController) {
    val title = when {
        navController.currentRoute == ScreenRoute.ANNOUNCEMENT_BOARD.route -> stringResource(R.string.top_bar_announcement_baord)
        navController.currentRoute?.startsWith(ScreenRoute.ANNOUNCEMENT_DETAIL.route) == true -> stringResource(
            R.string.top_bar_announcement_detail,
        )

        else -> ""
    }
    CenterAlignedTopAppBar(
        title = {
            navController.currentRoute?.let {
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
