package presentation.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

object BaseUi {
    private val firaSansFamily = FontFamily(
        Font(R.font.proxima_nova_regular, FontWeight.Light),
        Font(R.font.proxima_nova_regular, FontWeight.Normal),
        Font(R.font.proxima_nova_regular, FontWeight.Medium),
        Font(R.font.proxima_nova_bold, FontWeight.Bold)
    )
    val Mango = Color(0xFFFF9800)
    private val AlmostBlack = Color(android.graphics.Color.parseColor("#112233"))
    val styleDefault = androidx.compose.ui.text.TextStyle(
        fontWeight = FontWeight.Light,
        fontFamily = firaSansFamily,
        color = AlmostBlack,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
        fontSize = 20.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    )
}