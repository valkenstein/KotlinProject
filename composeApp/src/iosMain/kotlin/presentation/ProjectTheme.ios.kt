package presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ui.DarkColorScheme
import ui.LightColorScheme

@Composable
actual fun ProjectTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = ui.getTypography(),
        content = content
    )
}