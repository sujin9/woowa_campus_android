package woowacourse.campus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import woowacourse.campus.ui.navigation.BottomNavigationView
import woowacourse.campus.ui.navigation.CampusNavController
import woowacourse.campus.ui.navigation.NavigationGraph
import woowacourse.campus.ui.navigation.TopAppBarView
import woowacourse.campus.ui.theme.WoowaCampusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoowaCampusTheme {
                MainScreenView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreenView(
    navController: CampusNavController = rememberCampusNavController(),
) {
    Scaffold(
        bottomBar = {
            if (navController.isBottomBarVisible()) {
                BottomNavigationView(navController = navController)
            }
        },
        topBar = {
            if (navController.isTopAppBarVisible()) {
                TopAppBarView(navController = navController)
            }
        },
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun rememberCampusNavController(navController: NavHostController = rememberNavController()): CampusNavController {
    return remember(navController) {
        CampusNavController(navController)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WoowaCampusTheme {
        MainScreenView()
    }
}
