package presentation.component.invite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import domain.model.MapKey
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_spinner
import org.jetbrains.compose.resources.painterResource
import utils.stateRemember

@Composable
fun DropdownExample(
    modifier: Modifier,
    filters: List<MapKey>,
    showText: String,
    change: ((MapKey) -> Unit)? = null,
    content: @Composable RowScope.(MutableState<Boolean>) -> Unit,
) {
    val expanded = false.stateRemember()
    Row(modifier = modifier) {
        content.invoke(this, expanded)

        Spacer(modifier = Modifier.size(4.dp))
        Icon(
            modifier = Modifier.align(Alignment.CenterVertically),
            painter = painterResource(Res.drawable.ic_spinner),
            contentDescription = ""
        )
        DropdownMenu(
            modifier = modifier
                .padding(end = 16.dp)
                .wrapContentWidth(),
            expanded = expanded.value,
            offset = DpOffset(x = 0.dp, y = (-15).dp),
            onDismissRequest = { expanded.value = false },
        ) {
            filters.forEach {
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    //selectedText = it.value
                    change?.invoke(it)
                }, text = {
                    Row {
                        if (it.img.isNotEmpty()) {
                            CoilImage(
                                modifier = Modifier
                                    .height(17.dp)
                                    .width(17.dp),
                                imageModel = { it.img }, // loading a network image or local resource using an URL.
                                imageOptions = ImageOptions(
                                    contentScale = ContentScale.Fit,
                                )
                            )

                            Spacer(modifier = Modifier.size(8.dp))
                        }
                        Text(
                            it.value,
                            fontWeight = if (showText == it.value) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 16.sp,
                        )
                    }
                })
            }
        }
    }
}



