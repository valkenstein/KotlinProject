package presentation.component.dialogwindow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import domain.model.DialogNotification
import presentation.component.bonus.ButtonOk
import ui.BaseRed

@Composable
fun NotificationEnableDialog(
    data: DialogNotification = DialogNotification(),
    modifier: Modifier = Modifier.fillMaxHeight().fillMaxWidth(),
    blurClick: (() -> Unit)? = null,
    onBt1Click: (() -> Unit)? = null,
    onBt2Click: (() -> Unit)? = null
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = modifier
        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .fillMaxWidth()
//                    //.alpha(0.7f)
//                    //.background(Color.Black)
//                    .clickable(
//                        interactionSource = MutableInteractionSource(),
//                        indication = null
//                    ) {
//                        blurClick?.invoke()
//                    }
//            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color.White)
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier.wrapContentWidth()
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = data.description,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.02.em,
                    lineHeight = 23.sp,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.wrapContentWidth()
                )
                Spacer(modifier = Modifier.size(16.dp))
                ButtonOk(data.bt1.uppercase(), onClick = onBt1Click)
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = data.bt2.uppercase(),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.08.em,
                    fontSize = 12.sp,
                    color = BaseRed,
                    modifier = Modifier
                        .wrapContentWidth()
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            onBt2Click?.invoke()
                        }
                )
            }
        }
    }
}