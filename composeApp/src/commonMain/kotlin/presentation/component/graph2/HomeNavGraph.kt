package presentation.component.graph2

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import presentation.component.Contact.initContact
import presentation.component.bonus.BonusInit
import presentation.component.invite.InviteInitBottomSheet

@Composable
fun HomeNavGraph(navController: NavHostController ) {
    NavHost(
        navController = navController,
        route = Graph.DETAILS,
        startDestination = BottomBarScreen.Profile.route,
    ) {
        composable(route = DetailsScreen.Overview.route) {
            InviteInitBottomSheet {
                navController.navigate(Graph.AUTHENTICATION)
            }
        }
        composable(route = BottomBarScreen.Profile.route) {
            BonusInit()
        }
        composable(route = BottomBarScreen.Settings.route) {
            initContact()
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = DetailsScreen.Overview.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            //initContact()
            initContact(){
                navController.navigateUp()
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            initContact(){
                navController.navigateUp()
            }
//            ScreenContent(name = DetailsScreen.Overview.route) {
//                navController.popBackStack(
//                    route = DetailsScreen.Information.route,
//                    inclusive = false
//                )
//            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}