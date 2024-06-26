//package ui
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.PlatformTextStyle
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.LineHeightStyle
//import androidx.compose.ui.unit.sp
//import kotlinproject.composeapp.generated.resources.Res
//import kotlinproject.composeapp.generated.resources.proxima_nova_bold
//import kotlinproject.composeapp.generated.resources.proxima_nova_regular
//
////expect fun getProximaNovaFontFamily(): FontFamily
////
//val firaSansFamily = getProximaNovaFontFamily()
//object BaseUi {
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
//}