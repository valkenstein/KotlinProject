package presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarBase(
    modifier: Modifier = Modifier,
    title: String = "title",
    isHideAnimate: Boolean = false,
    isHide: Boolean = false,
    onClick: () -> Unit
) {
    val targetValue = if (isHideAnimate) 200f else 0f
    val animatedValue = remember { Animatable(0f) }
    LaunchedEffect(isHideAnimate) {
        animatedValue.animateTo(targetValue, animationSpec = tween(durationMillis = 600))
    }
    TopAppBar(
        modifier = modifier,
        title = {
            ToolbarCustom(Modifier.offset(y = animatedValue.value.dp), title, isHide, onClick)
        }
//        elevation = 0.dp,
//        contentPadding = WindowInsets.systemBars
//            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
//            .asPaddingValues(),
//        backgroundColor = colorResource(id = R.color.white)
    )

}

@Composable
fun ToolbarCustom(modifier: Modifier, title: String, isHide: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
    ) {

        Image(
            modifier = Modifier
                .height(16.dp)
                .wrapContentWidth()
                .align(Alignment.CenterStart)
                .padding(start = 5.dp)
                .clickable {
                    onClick.invoke()
                },
            contentScale = ContentScale.Crop,
            painter = painterResource(Res.drawable.ic_arrow_back),
            contentDescription = "logo",
        )
        if (!isHide)
            Text(
                modifier = modifier
                    .wrapContentHeight()
                    .align(Alignment.Center),
                //style = BaseUi.styleDefault,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.04.em,
                fontSize = 12.sp,
                text = title.uppercase()
            )
    }
}