package presentation

import androidx.compose.runtime.Composable

@Composable
expect fun ProjectTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)