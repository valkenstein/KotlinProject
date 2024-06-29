package presentation.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import domain.model.InviteCellDom
import domain.model.StatusInvite
import presentation.component.bonus.ButtonOk
import ui.BaseGreen
import ui.Yellow
import ui.gray_filters
import utils.toPrice

@Composable
fun ComposeShareInviteMiniBottomSheet(
    shareInvite: InviteCellDom = InviteCellDom(),
    onCreateInviteAgain: ((InviteCellDom) -> Unit)? = null
) {
    ComposeBaseBottomSheet(
        Modifier
            .padding(horizontal = 16.dp)
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = shareInvite.code,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.02.em,
            )
            Text(
                text = shareInvite.dateTimeCreateText,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        Spacer(modifier = Modifier.size(32.dp))
        Row {
            Text(
                text = shareInvite.status.title.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.08.em,
            )
            Spacer(modifier = Modifier.size(8.dp))
            //if (shareInvite.status != StatusInvite.EXPIRED)
            Text(
                text = shareInvite.dateTimeActivatedText,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.24.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        if (shareInvite.status == StatusInvite.EXPIRED) {
            Spacer(modifier = Modifier.size(32.dp))
            ButtonOk(
                "активировать снова".uppercase(),
                modifier = Modifier,
                modifierIcon = Modifier.padding(start = 10.dp)
            ) {
                onCreateInviteAgain?.invoke(shareInvite)
            }
            Spacer(modifier = Modifier.size(16.dp))
        } else {
            Spacer(modifier = Modifier.size(32.dp))
            Row {
                Text(
                    buildAnnotatedString {
                        shareInvite.customerInfo.forEach {
                            withStyle(style = SpanStyle(fontWeight = if (it.bold) FontWeight.Bold else FontWeight.Normal)) {
                                append(it.text)
                            }
                        }
                    },
                    //text = shareInvite.customerInfo,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 26.sp,
                    letterSpacing = 0.02.em,
                )
//                Spacer(modifier = Modifier.size(8.dp))
//                Text(
//                    text = " +7 (916) **** 241".uppercase(),
//                    style = BaseUi.styleDefault,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 12.sp,
//                    lineHeight = 26.sp,
//                    letterSpacing = 0.08.em,
//                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)),
                progress = shareInvite.sliderPrice,
                color =   if (shareInvite.currentIntTotal >= shareInvite.maxTotal) BaseGreen else  Yellow,
                trackColor = gray_filters,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Box(Modifier.fillMaxWidth()) {
                Text(
                    text = shareInvite.currentTotal,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.02.em,
                    modifier = Modifier
                )
                Text(
                    text = shareInvite.maxTotal.toString().toPrice(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.24.sp,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}