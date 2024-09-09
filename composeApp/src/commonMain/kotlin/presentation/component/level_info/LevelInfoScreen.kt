package presentation.component.level_info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import domain.model.MapKey
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_cross
import kotlinproject.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.component.ToolbarBase
import presentation.component.invite.DropdownExample
import presentation.mvvm.ContactListViewModel
import presentation.mvvm.LevelInformationViewModel
import ui.BaseBlack
import ui.bt_disable
import utils.stateRemember

@Preview()
@Composable
fun LazyLevel(
) {
    Column(Modifier.fillMaxSize()) {
        ToolbarBase(title = "Cкидки по уровням", isHideAnimate = false, isHide = false) {
            //findNavController().popBackStack()
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyLevelInfo()
        //Spacer(modifier = Modifier.height(56.dp))
    }
}
@OptIn(KoinExperimentalAPI::class, ExperimentalFoundationApi::class)
@Preview()
@Composable
fun LazyLevelInfo(
) {
    val viewModel: LevelInformationViewModel = koinViewModel()
    val categories by viewModel.dataItems.collectAsState()
    val dropItems by viewModel.dropDownItems.collectAsState()
    var query by "".stateRemember()
    val currentLevel = viewModel.getSelectedLevel()
    var selectedDrop by MapKey(currentLevel, "Базовый").stateRemember()

    LaunchedEffect(dropItems) {
        dropItems.find { it.key == currentLevel }?.let {
            selectedDrop = it
        }
    }

    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(1) {
            Row {
                CoilImage(
                    modifier = Modifier
                        .height(26.dp)
                        .width(26.dp),
                    imageModel = { selectedDrop.img }, // loading a network image or local resource using an URL.
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )
                )
//                Image(
//                    modifier = Modifier
//                        .height(26.dp)
//                        .width(26.dp),
//                    painter = rememberAsyncImagePainter(model = selectedDrop.img),
//                    contentDescription = selectedDrop.img,
//                    contentScale = ContentScale.Fit,
//                )
                //Spacer(modifier = Modifier.size(8.dp))
                DropdownExample(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    dropItems,
                    selectedDrop.value,
                    change = {
                        selectedDrop = it
                        viewModel.selectDropDown(it)
                    }
                ) { expanded ->
                    Text(
                        fontSize = 20.sp,
                        text = selectedDrop.value,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .wrapContentWidth()
                            .clickable {
                                expanded.value = true
                            })
                }
            }
        }

        if (categories.isNotEmpty() || query.isNotEmpty())
            items(1) {
                Spacer(modifier = Modifier.size(24.dp))
                InputText2(Modifier, hint = "Поиск по брендам", isSearch = true) {
                    viewModel.queryChange(it)
                    query = it
                }
            }

        if (categories.isEmpty()) items(1) {
            Spacer(modifier = Modifier.height(32.dp))
            if (query.isNotEmpty())
                EmptySearchCategory("Такой бренд не найден")
            else
                EmptySearchCategory("Дополнительных скидок \nне предусмотрено")
        } else categories.forEach { (category, items) ->
            item {
                CategoryHeader(modifier = Modifier.animateItemPlacement(), category)
            }
            items(items.count(), key = { items[it].title }) { index ->
                val procent =
                    if (items[index].discounts[selectedDrop.key] != "0") "– " + items[index].discounts[selectedDrop.key] + " %" else ""
                CategoryLevelItem(modifier = Modifier.animateItemPlacement(), items[index].title, procent, query)
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun EmptySearchCategory(title: String) {
    Text(
        modifier = Modifier,//.animateItemPlacement(),
        text = title,
        fontSize = 16.sp,
        letterSpacing = 0.02.em,
        color = bt_disable,
        fontWeight = FontWeight.Normal,
        //  modifier = Modifier.align(Alignment.CenterStart)
    )
}

@Composable
fun CategoryLevelItem(modifier: Modifier, title: String, discount: String, query: String) {
    Spacer(modifier = Modifier.height(14.dp))
    val start = title.indexOf(string = query, ignoreCase = true)
    val end = query.length
//    if (start == -1) {
//        start = 0
//        end = 0
//    }
    //Log.e("CategoryLevelItem", " $start = $title = $query")
    val text: AnnotatedString = buildAnnotatedString {
        append(title)
        if (start != -1)
            addStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
                start = title.indexOf(string = query, ignoreCase = true),
                end = start + end
            )
    }
    Box(modifier = modifier.fillMaxWidth(),) {
        Text(
            text = text,
            modifier = Modifier,//.animateItemPlacement(),
            // text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            //  modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            modifier = Modifier.align(Alignment.TopEnd),//.animateItemPlacement(),
            text = discount,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.02.em,
        )
    }
    Spacer(modifier = Modifier.height(14.dp))
}

@Preview()
@Composable
fun CategoryHeader(modifier: Modifier, title: String = "simple") {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = title,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
          modifier = modifier,
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview()
@Composable
fun InputText2(
    modifier: Modifier = Modifier,
    hint: String = "Что добавить?",
    isSearch: Boolean = false,
    onValueChange: (String) -> Unit = {}
) {
    var text by rememberSaveable { mutableStateOf("") }
    val focusRequester by FocusRequester().stateRemember()
    var isFocused by false.stateRemember()

    LaunchedEffect(Unit) {
        //focusRequester.requestFocus()
    }

    BasicTextField(
        value = text,
        modifier = modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .focusModifier()
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        onValueChange = {
            text = it.capitalize()
            onValueChange.invoke(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        decorationBox = { innertTextField ->
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .padding(end = 25.dp)
            ) {
                innertTextField()
            }
            Box(modifier = Modifier.height(32.dp)) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(if (isFocused) BaseBlack else bt_disable)
                )
                if (text.isEmpty())
                    Text(
                        text = hint,
                        color = Color.Gray,
                        fontSize = 20.sp,
                        //  modifier = Modifier.align(Alignment.CenterStart)
                    )

                if (text.isNotEmpty() || isSearch)
                    Image(
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                text = ""
                                onValueChange.invoke("")
                            },
                        painter = painterResource(if (isSearch && text.isEmpty()) Res.drawable.ic_search else Res.drawable.ic_cross),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                    )
            }
        }
    )
}
