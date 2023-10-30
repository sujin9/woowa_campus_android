package woowacourse.campus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Green01_00D473,
    onPrimary = Grey03_AAAAAA,
    background = Grey02_F5F5F5,
    outlineVariant = Grey04_DFDFDF,
    surface = Black02_333333,
    onSurface = Grey01_666666,
    surfaceVariant = White01_FFFFFF,
    onSurfaceVariant = Black01_222222,
)

@Composable
fun WoowaCampusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
