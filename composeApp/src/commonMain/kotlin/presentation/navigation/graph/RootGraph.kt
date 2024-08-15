//package presentation.navigation.graph
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import homeNavGraph
//import onBoardingNavGraph
//import presentation.navigation.graph.configRoute.Screen
//import presentation.navigation.graph.configRoute.navigateTo
//import subscriptionScreen
//
//@Composable
//fun RootNavGraph(
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    startDestination: Screen
//) {
//    NavHost(
//        navController = navController,
//        route = "root_host",
//        startDestination = startDestination.route,
//        modifier = modifier,
//    ) {
//
//        val navigateBack: () -> Unit = {
//            navController.navigateUp()
//        }
//
//        //Nested Navigation Graphs
//        onBoardingNavGraph(onNavigateToRoot = navController::navigateTo)
//        homeNavGraph(onNavigateToRoot = navController::navigateTo)
//
//        //Root screens
//        subscriptionScreen(onNavigateBack = navigateBack)
//    }
//}