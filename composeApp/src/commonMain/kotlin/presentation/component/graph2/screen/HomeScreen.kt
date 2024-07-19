package presentation.component.graph2.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import presentation.component.graph2.BottomBarScreen
import presentation.component.graph2.HomeNavGraph
import ui.BaseRed

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
            ) {
                HomeNavGraph(navController = navController)
            }
        },
        bottomBar = { BottomBar(navController = navController) }
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(
            modifier = Modifier.height(56.dp),
            containerColor = Color.White
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
            //TabNavigationItem(BonusTab)
//            TabNavigationItem(InviteTab)
//            TabNavigationItem(VitrinaTab)
//            TabNavigationItem(ProfileTab)
//            TabNavigationItem(BasketTab)
        }
        //}
//        BottomNavigation {
//            screens.forEach { screen ->
//                AddItem(
//                    screen = screen,
//                    currentDestination = currentDestination,
//                    navController = navController
//                )
//            }
//        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val interactionSource = remember { MutableInteractionSource() }
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    Box(modifier = Modifier
        .padding(vertical = 8.dp)
        .fillMaxHeight()
        .weight(1f)
        .clickable(
            interactionSource = interactionSource,
            indication = rememberRipple(bounded = false, color = Color.Gray),
        ) {
            if (!selected)
                try {
                    val route = screen.route
                    val backStackEntry = navController.getBackStackEntry(route)
                    val savedStateHandle = backStackEntry.savedStateHandle
//                    navController.popBackStack(screen.route, false)
                    navController.navigate(route) {
                        popUpTo(route) { inclusive = true }
                    }
                } catch (e: Exception) {
                    navController.navigate(screen.route) {
                        popUpTo(screen.route ?: "") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }


            //tabNavigator.current = tab
        }) {

        Image(
            imageVector = screen.icon,
            contentDescription = "back",
            alignment = Alignment.Center,
            colorFilter = ColorFilter.tint(if (selected) BaseRed else Color.Black),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            letterSpacing = 0.08.em,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.BottomCenter),
            text = screen.title,
            fontSize = 8.sp,
            color = if (selected) BaseRed else Color.Black
        )
    }
}