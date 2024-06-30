package utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_invite_bar
import org.jetbrains.compose.resources.painterResource
import ui.BaseRed

@Composable
fun <T> T.stateRemember() = remember { mutableStateOf(this) }
fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current == tab

    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = Modifier
        .padding(vertical = 8.dp)
        .fillMaxHeight()
        .weight(1f)
        .clickable(
            interactionSource = interactionSource,
            indication = rememberRipple(bounded = false, color = Color.Gray),
            ) {
            tabNavigator.current = tab
        }) {
        Image(
            painter = tab.options.icon!!,
            contentDescription = "back",
            alignment = Alignment.Center,
            colorFilter = ColorFilter.tint(if (selected) BaseRed else Color.Black),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            letterSpacing = 0.08.em,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.BottomCenter),
            text = tab.options.title,
            fontSize = 8.sp,
            color = if (selected) BaseRed else Color.Black
        )
    }
//    NavigationBarItem(
//        selected = tabNavigator.current == tab,
//        onClick = { tabNavigator.current = tab },
//        icon = {
//            tab.options.icon?.let { painter ->
//                Icon(
//                    painter = painter,
//                    contentDescription = tab.options.title
//                )
//            }
//        },
//        label = {
//            Text(text = tab.options.title)
//        }
//    )
}

