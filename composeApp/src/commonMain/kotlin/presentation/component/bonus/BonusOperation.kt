package presentation.component.bonus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import domain.model.BonusAccountItemDom
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_r
import org.jetbrains.compose.resources.painterResource
import ui.BaseBlack
import ui.BaseGreen
import ui.BaseRed
import ui.BtDisable
import utils.formattedNumber


@Composable
fun LazyBonusAccounts(
    title: String,
    list: List<BonusAccountItemDom>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        if (list.isNotEmpty())
            items(1) {
                Spacer(modifier = Modifier.height(20.dp))
                TittleBonus(title)
                Spacer(modifier = Modifier.height(40.dp))
            }
        items(list.count()) { item ->
            ItemBonusAccount(list[item])
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun TittleBonus(title: String) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = androidx.compose.ui.graphics.Color.Black)) {
                append(title.formattedNumber())
            }
            withStyle(style = SpanStyle(color = androidx.compose.ui.graphics.Color.Red)) {
                append(" Red")
            }
            withStyle(style = SpanStyle(color = androidx.compose.ui.graphics.Color.Black)) {
                append("-баллов")
            }
        },
        modifier = Modifier.padding(16.dp),
        //style = styleDefault,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    )
}

@Composable
fun OperationsBonus(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        //style = styleDefault,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.08.em,
       color = BaseRed,
        modifier = modifier
    )
}

@Composable
fun ItemBonusAccount(
    bonusAccountItemDom: BonusAccountItemDom = BonusAccountItemDom(
        date = "21 ок",
        text = "text",
        bonus = "125",
        type = "+",
    )
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
    ) {
        Column(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
        ) {
            Text(
                text = bonusAccountItemDom.date.uppercase(),
             //   style = styleDefault,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.08.em,
                color = BtDisable,
                fontSize = 12.sp,
                modifier = Modifier.wrapContentWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = bonusAccountItemDom.text,
              //  style = styleDefault,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.02.em,
                color = BaseBlack,
                fontSize = 16.sp,
                modifier = Modifier.wrapContentWidth()
            )
        }
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = bonusAccountItemDom.type + " " + bonusAccountItemDom.bonus.formattedNumber()
                    .trim(),
              //  style = styleDefault,
                fontWeight = FontWeight.Bold,
                color = if (bonusAccountItemDom.type == "+")
                    BaseGreen
                else BaseRed,
                fontSize = 20.sp,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                painter = painterResource(Res.drawable.ic_r),
                contentDescription = "",
                modifier = Modifier
                    .height(14.dp)
                    .align(Alignment.CenterVertically),
                colorFilter = ColorFilter.tint(
                    if (bonusAccountItemDom.type == "+")
                        BaseGreen
                    else BaseRed
                )
            )
        }
    }
}