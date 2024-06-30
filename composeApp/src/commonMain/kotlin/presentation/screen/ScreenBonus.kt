package presentation.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.component.bonus.BonusInit

class ScreenBonus : Screen {
    @Composable
    override fun Content() {
        BonusInit(LocalNavigator.currentOrThrow)
    }
}