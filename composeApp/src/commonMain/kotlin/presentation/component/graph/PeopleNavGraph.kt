//package presentation.component.graph
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import chatListScreen
//import peopleNavGraph
//import presentation.component.graph.configRoute.Screen
//import profileScreen
//
//@Composable
//fun PeopleNavGraph(
//    modifier: Modifier = Modifier,
//    navController: NavHostController,
//    onNavigateToRoot: (Screen) -> Unit
//) {
//    NavHost(
//        navController = navController,
//        startDestination = Screen.ChatList.route,
//        modifier = modifier,
//    ) {
//
//        chatListScreen()
//        peopleNavGraph()
//        profileScreen(onNavigateTo = onNavigateToRoot)
//
//    }
//}