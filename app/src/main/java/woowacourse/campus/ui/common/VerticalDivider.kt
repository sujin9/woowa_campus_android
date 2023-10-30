package woowacourse.campus.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun VerticalDivider(width: Dp) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 8.dp)
            .width(width)
            .background(color = MaterialTheme.colorScheme.onSurface),
    )
}
