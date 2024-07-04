package presentation.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.component.invite.InviteInitBottomSheet

class ScreenInvite : Screen {
    @Composable
    override fun Content() {
        //InviteInitBottomSheet(LocalNavigator.currentOrThrow)
    }
}