package presentation.screen.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_bars_tab_bar_x_catalog
import kotlinproject.composeapp.generated.resources.ic_bars_tab_bar_x_user
import org.jetbrains.compose.resources.vectorResource
import presentation.screen.ScreenBonus
import presentation.screen.ScreenInvite

object ProfileTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "профиль".uppercase()
            val icon = rememberVectorPainter(vectorResource(Res.drawable.ic_bars_tab_bar_x_user))

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(ScreenBonus()) { navigator ->
            SlideTransition(navigator)
        }
    }
}