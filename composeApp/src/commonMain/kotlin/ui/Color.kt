package ui

import androidx.compose.ui.graphics.Color

fun hexToColor(hex: String): Color {
    val hexColor = if (hex.startsWith("#")) hex.substring(1) else hex
    val colorInt = hexColor.toLong(16).toInt()

    // Если hex не содержит альфа-канала, добавим FF как значение по умолчанию для альфа-канала
    val colorWithAlpha = if (hexColor.length == 6) {
        0xFF000000.toInt() or colorInt
    } else {
        colorInt
    }

    return Color(colorWithAlpha)
}

// LIGHT
val GreenPrimaryLight = Color(0xff006e26)
val OnGreenLight = Color(0xffffffff)
val GreenContainerLight = Color(0xff6cff82)
val OnGreenContainerLight = Color(0xff002106)

val GreenSecondaryLight = Color(0xff526350)
val OnGreenSecondaryLight = OnGreenLight
val GreenSecondaryContainerLight = Color(0xffd4e8d0)
val OnGreenSecondaryContainerLight = Color(0xff101f10)

val GreenTertiaryLight = Color(0xff39656b)
val OnGreenTertiaryLight = OnGreenLight
val GreenTertiaryContainerLight = Color(0xffbcebf2)
val OnGreenTertiaryContainerLight = Color(0xff001f23)

val ErrorLight = Color(0xffba1a1a)
val OnErrorLight = Color(0xffffffff)
val ErrorContainerLight = Color(0xffffdad6)
val OnErrorContainerLight = Color(0xff410002)

val BackgroundLight = Color(0xfffcffff)
val OnBackgroundLight = Color(0xff1a1c19)
val SurfaceLight = BackgroundLight
val OnSurfaceLight = OnBackgroundLight
val SurfaceVariantLight = Color(0xffdee5d9)
val OnSurfaceVariantLight = Color(0xff424940)

val OutlineLight = Color(0xff72796f)

// DARK
val GreenPrimaryDark = Color(0xff00e559)
val OnGreenDark = Color(0xff003910)
val GreenContainerDark = Color(0xff00531b)
val OnGreenContainerDark = Color(0xff6cff82)

val GreenSecondaryDark = Color(0xffb9ccb5)
val OnGreenSecondaryDark = OnGreenDark
val GreenSecondaryContainerDark = Color(0xff3a4b39)
val OnGreenSecondaryContainerDark = Color(0xffd4e8d0)

val GreenTertiaryDark = Color(0xffa1ced5)
val OnGreenTertiaryDark = Color(0xff00363c)
val GreenTertiaryContainerDark = Color(0xff1f4d53)
val OnGreenTertiaryContainerDark = Color(0xffbcebf2)

val ErrorDark = Color(0xffffb4ab)
val OnErrorDark = Color(0xff690005)
val ErrorContainerDark = Color(0xff93000a)
val OnErrorContainerDark = Color(0xffffdad6)

val BackgroundDark = Color(0xff1a1c19)
val OnBackgroundDark = Color(0xffe2e3dd)
val SurfaceDark = BackgroundDark
val OnSurfaceDark = OnBackgroundDark
val SurfaceVariantDark = Color(0xff424940)
val OnSurfaceVariantDark = Color(0xffc2c9bd)
val OutlineDark = Color(0xff72796f)

val BaseRed = hexToColor("#E21B25") //Color(0xff72796f)
val BaseGreen = hexToColor("#31c368")
val Yellow = hexToColor("#31c368")
val gray_filters = hexToColor("#F5F6FA")
val gray_dialog = hexToColor("#E5E5E5")
val bt_disable = hexToColor("#989898")
val gray_category = hexToColor("#F9F9F9")
val BaseBlack = hexToColor("#112233")
val BtDisable = hexToColor("#989898")

