package ui

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ui.BaseUi.getTypography

// Set of Material typography styles to start with

@Composable
fun getTypography() = Typography(
    bodySmall = TextStyle(
        fontFamily = getTypography(),
        fontWeight = FontWeight.Normal,
        //fontSize = 16.sp,
        //platformStyle = androidx.compose.ui.text.PlatformTextStyle(includeFontPadding = false),
        //letterSpacing = 0.5.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = getTypography(),
        fontWeight = FontWeight.Normal,
        //fontSize = 16.sp,
        //platformStyle = androidx.compose.ui.text.PlatformTextStyle(includeFontPadding = false),
        //letterSpacing = 0.5.sp,
    )
)

//val proximaNovaFontFamily = FontFamily(
//     Font("ProximaNova-Regular")
//)

//val Typography2 = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = proximaNovaFontFamily,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//)


/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
//)