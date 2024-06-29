package presentation.component.invite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import domain.model.MapKey
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_spinner
import org.jetbrains.compose.resources.painterResource
import utils.stateRemember

@Composable
fun DropdownExample(
    modifier: Modifier,
    filters: List<MapKey>,
    selectedFilter: MapKey,
    change: (MapKey) -> Unit
) {
    var expanded by false.stateRemember()
    var selectedText by "все".stateRemember()

    Row(modifier = modifier) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            letterSpacing = 0.06.em,
            text = selectedFilter.value.uppercase(),
            modifier = Modifier
                .wrapContentWidth()
                .clickable {
                    expanded = true
                })
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
            expanded = expanded,
            offset = DpOffset(x = 0.dp, y = (-15).dp),
            onDismissRequest = { expanded = false },
        ) {
            filters.forEach {
                DropdownMenuItem(text = {
                    Text(
                        it.value,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
                    ,onClick = {
                    expanded = false
                    selectedText = it.value
                    change.invoke(it)
                })
            }
        }
    }
}
