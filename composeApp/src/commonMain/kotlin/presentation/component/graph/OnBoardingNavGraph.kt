//package presentation.component.graph
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import onBoardingFirstScreen
//import onBoardingSecondScreen
//import onBoardingThirdScreen
//import presentation.component.graph.configRoute.Screen
//
//@Composable
//fun OnBoardingNavGraph(
//    modifier: Modifier = Modifier,
//    navController: NavHostController
//) {
//    NavHost(
//        navController = navController,
//        startDestination = Screen.OnBoardingFirst.route,
//        modifier = modifier,
//    ) {
//
//        onBoardingFirstScreen()
//        onBoardingSecondScreen()
//        onBoardingThirdScreen()
//    }
//}