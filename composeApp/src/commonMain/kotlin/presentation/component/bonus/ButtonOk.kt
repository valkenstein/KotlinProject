package presentation.component.bonus

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ui.BaseRed
import ui.bt_disable
import ui.gray_category


@Composable
fun ButtonOk(
    txt: String,
    isDisable: Boolean = false,
    modifier: Modifier = Modifier,
    modifierIcon: Modifier = Modifier,
    colorButton: Color = if (isDisable)  gray_category else BaseRed,
    colorText: Color = if (isDisable) bt_disable else Color.White,
    icon: DrawableResource? = null,
    onClick: (() -> Unit)? = null
) {
    Button(modifier = modifier
        .fillMaxWidth()
        .height(60.dp)
        .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorButton,
            contentColor = Color.White
        ),
        onClick = { onClick?.invoke() }) {
        Text(
            text = txt,
            fontWeight = FontWeight.Bold,
            color = colorText,
            fontSize = 12.sp,
            letterSpacing = 0.08.em,
            textAlign = TextAlign.Center,
            lineHeight = 23.sp,
        )
        icon?.let {
            Icon(
                modifier = modifierIcon,
                painter = painterResource(it), contentDescription = null
            )
        }
    }
}