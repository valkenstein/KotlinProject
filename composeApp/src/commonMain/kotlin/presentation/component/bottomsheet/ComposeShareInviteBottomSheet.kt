package presentation.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import domain.model.InviteCellDom
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_clip
import kotlinproject.composeapp.generated.resources.ic_share
import org.jetbrains.compose.resources.painterResource
import presentation.component.bonus.ButtonOk
import ui.BaseRed
import ui.gray_filters

@Composable
fun ComposeShareInviteBottomSheet(
    shareInvite: InviteCellDom = InviteCellDom(),
    onCopyCode: ((String) -> Unit)? = null,
    onShareInvite: ((String) -> Unit)? = null,
    onRemoveClick: ((InviteCellDom) -> Unit)? = null
) {
    ComposeBaseBottomSheet(
        Modifier
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.clickable {
                onCopyCode?.invoke(shareInvite.code)
            }) {
                Text(
                    text = shareInvite.code,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    lineHeight = 26.sp,
                    letterSpacing = 0.02.em,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Image(
                    modifier = Modifier.align(CenterVertically),
                    painter = painterResource(Res.drawable.ic_clip),
                    contentDescription = null
                )
            }
            Text(
                text = "${shareInvite.dateTimeCreateText}",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }

        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = "удалить".uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            lineHeight = 26.sp,
            color = BaseRed,
            letterSpacing = 0.08.em,
            modifier = Modifier.clickable {
                onRemoveClick?.invoke(shareInvite)
            }
        )
        Spacer(modifier = Modifier.size(32.dp))
        Row {
            Text(
                text = "ожидает активации".uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.08.em,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "${shareInvite.dateTimeActiveToText}",
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.02.em,
            )
        }

        Spacer(modifier = Modifier.size(8.dp))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)),
            progress = shareInvite.slider,
            color = BaseRed,
            trackColor = gray_filters
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = shareInvite.statusTime,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.02.em,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.size(16.dp))
        val link = shareInvite.linkActivate
//        Image(
//            modifier = Modifier
//                .width(160.dp)
//                .height(160.dp)
////                .fillMaxWidth(0.42f)
////                .fillMaxHeight(0.42f)
//                .align(Alignment.CenterHorizontally),
//
//            bitmap =  LocalContext.current.generateQRCode(link).asImageBitmap(),
//            contentDescription = null,
//            contentScale = ContentScale.Crop
//        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Покажите QR-код коллеге, или поделитесь с ним ссылкой на активацию инвайт-кода.",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 23.sp,
            letterSpacing = 0.02.em,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.size(32.dp))
        ButtonOk(
            "Поделиться".uppercase(),
            modifier = Modifier,
            icon = Res.drawable.ic_share,
            modifierIcon = Modifier.padding(start = 10.dp)
        ) {
            onShareInvite?.invoke(link)
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}
