package ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


//val DarkColorScheme: Colors = darkColors(
//    primary = GreenPrimaryDark,
//    //primaryVariant = GreenPrimaryVariantDark,
//    secondary = GreenSecondaryDark,
//    //secondaryVariant = GreenSecondaryVariantDark,
//    background = BackgroundDark,
//    surface = SurfaceDark,
//    error = ErrorDark,
//    onPrimary = OnGreenDark,
//    onSecondary = OnGreenSecondaryDark,
//    onBackground = OnBackgroundDark,
//    onSurface = OnSurfaceDark,
//    onError = OnErrorDark,
//    // If you need more custom colors, you can add them manually later in the theme
//)
//
//// Light color scheme
//val LightColorScheme: Colors = lightColors(
//    primary = GreenPrimaryLight,
//    //primaryVariant = GreenPrimaryVariantLight,
//    secondary = GreenSecondaryLight,
//    //secondaryVariant = GreenSecondaryVariantLight,
//    background = BackgroundLight,
//    surface = SurfaceLight,
//    error = ErrorLight,
//    onPrimary = OnGreenLight,
//    onSecondary = OnGreenSecondaryLight,
//    onBackground = OnBackgroundLight,
//    onSurface = OnSurfaceLight,
//    onError = OnErrorLight,
//    // If you need more custom colors, you can add them manually later in the theme
//)

val DarkColorScheme = darkColorScheme(
    primary = GreenPrimaryDark,
    secondary = GreenSecondaryDark,
    tertiary = GreenTertiaryDark,
    onPrimary = OnGreenDark,
    primaryContainer = GreenContainerDark,
    onPrimaryContainer = OnGreenContainerDark,
    onSecondary = OnGreenSecondaryDark,
    secondaryContainer = GreenSecondaryContainerDark,
    onSecondaryContainer = OnGreenSecondaryContainerDark,
    onTertiary = OnGreenTertiaryDark,
    onTertiaryContainer = OnGreenTertiaryContainerDark,
    tertiaryContainer = GreenTertiaryContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    outline = OutlineDark,
)

val LightColorScheme = lightColorScheme(
    primary = Color.White,
    secondary = GreenSecondaryLight,
    tertiary = GreenTertiaryLight,
    onPrimary = OnGreenLight,
    primaryContainer = GreenContainerLight,
    onPrimaryContainer = OnGreenContainerLight,
    onSecondary = OnGreenSecondaryLight,
    secondaryContainer = GreenSecondaryContainerLight,
    onSecondaryContainer = OnGreenSecondaryContainerLight,
    onTertiary = OnGreenTertiaryLight,
    onTertiaryContainer = OnGreenTertiaryContainerLight,
    tertiaryContainer = GreenTertiaryContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    outline = OutlineLight,
)