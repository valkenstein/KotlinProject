package presentation.component.switch_component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.BaseRed
import ui.bt_disable
import utils.stateRemember

interface SwitchComponent

@Preview
@Composable
fun SwitchCustom(
    modifier: Modifier = Modifier,
    text: String = "",
    isChecked: Boolean = false,
    onValueChange: (Boolean) -> Unit = { }
) {
    var checked by isChecked.stateRemember()
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Switch(
                modifier = Modifier
                    .background(
                        color = if (checked) BaseRed else bt_disable,
                        shape = RoundedCornerShape(50)
                    )
                    // .align(Alignment.TopEnd)
                    .width(45.dp)
                    .height(26.dp),
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onValueChange.invoke(it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = BaseRed.copy(alpha = 1f),
                    disabledCheckedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    disabledUncheckedThumbColor = Color.White,
                    uncheckedTrackColor = bt_disable.copy(alpha = 0.5f),
//                    checkedTrackAlpha = 1f,
//                    uncheckedTrackAlpha = 1f,
                )
            )
        }

        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier.clickable {
                checked = !checked
            },
            text = text,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            letterSpacing = 0.02.em,
            lineHeight = 23.sp,
        )
    }
}