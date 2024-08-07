package ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.proxima_nova_bold
import kotlinproject.composeapp.generated.resources.proxima_nova_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi

//expect fun getProximaNovaFontFamily(): FontFamily
//

object BaseUi {
    @Composable
    fun getTypography() = FontFamily(
            Font(Res.font.proxima_nova_regular, FontWeight.Normal),
            Font(Res.font.proxima_nova_regular, FontWeight.Medium),
            Font(Res.font.proxima_nova_bold, FontWeight.Bold)
        )


//
//    val font: Font = MR.fonts.Raleway.italic.asFont(
//        weight = FontWeight.Normal, // optional
//        style = FontStyle.Normal // optional
//    )
//    private val firaSansFamily = FontFamily(
//        androidx.compose.ui.text.font.Font(Res.font.proxima_nova_regular, FontWeight.Light),
//        Font(Res.font.proxima_nova_regular, FontWeight.Normal),
//        Font(Res.font.proxima_nova_regular, FontWeight.Medium),
//        Font(Res.font.proxima_nova_bold, FontWeight.Bold)
//    )
//    val Mango = Color(0xFFFF9800)
//    private val AlmostBlack = Color(Color.parseColor("#112233"))
//    val styleDefault = androidx.compose.ui.text.TextStyle(
//        fontWeight = FontWeight.Light,
//        fontFamily = firaSansFamily,
//        color = AlmostBlack,
//        platformStyle = PlatformTextStyle(
//            includeFontPadding = false
//        ),
//        fontSize = 20.sp,
//        lineHeightStyle = LineHeightStyle(
//            alignment = LineHeightStyle.Alignment.Center,
//            trim = LineHeightStyle.Trim.None
//        )
//    )
}