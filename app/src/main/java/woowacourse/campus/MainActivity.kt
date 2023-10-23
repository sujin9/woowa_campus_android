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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import woowacourse.campus.ui.navigation.BottomNavigationView
import woowacourse.campus.ui.navigation.NavigationGraph
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
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = { BottomNavigationView(navController = navController) }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NavigationGraph(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WoowaCampusTheme {
        MainScreenView()
    }
}
