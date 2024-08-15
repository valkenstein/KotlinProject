package presentation.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import presentation.ProjectTheme
import presentation.navigation.graph2.RootNavigationGraph

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


