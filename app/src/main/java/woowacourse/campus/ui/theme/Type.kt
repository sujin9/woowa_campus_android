package woowacourse.campus.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import woowacourse.campus.R

// Set of Material typography styles to start with
private val hannaFontFamily = FontFamily(
    Font(resId = R.font.bm_hanna_pro, weight = FontWeight.Bold),
    Font(resId = R.font.bm_hanna_air, weight = FontWeight.Light),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = hannaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        lineHeight = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = hannaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        lineHeight = 22.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = hannaFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        lineHeight = 20.sp,
    ),
)
