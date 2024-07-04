package presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import presentation.ProjectTheme
import presentation.component.graph2.RootNavigationGraph

@Composable
fun AppProject(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    ProjectTheme(
        darkTheme,
        dynamicColor
    ) {
        val navController = rememberNavController()
        RootNavigationGraph(navController = navController)

//        TabNavigator(BonusTab) {
//            Scaffold(
//                content = { paddingValues ->
//                    Box(
//                        modifier = Modifier
//                            .padding(bottom = paddingValues.calculateBottomPadding())
//                    ) {
//                        CurrentTab()
//                    }
//                },
//                bottomBar = {
//                    NavigationBar(
//                        modifier = Modifier.height(56.dp),
//                        containerColor = Color.White
//                    ) {
//                        TabNavigationItem(BonusTab)
//                        TabNavigationItem(InviteTab)
//                        TabNavigationItem(VitrinaTab)
//                        TabNavigationItem(ProfileTab)
//                        TabNavigationItem(BasketTab)
//                    }
//                }
//            )
//        }
    }
}


