package presentation.component.invite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.pullrefresh.PullRefreshIndicator
import androidx.compose.material3.pullrefresh.pullRefresh
import androidx.compose.material3.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import domain.model.DialogNotification
import domain.model.InviteCellDom
import domain.model.StateInvite
import domain.model.StatusInvite
import domain.model.ViewEvent
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.LottieConstants
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_invite_bar
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.component.ToolbarBase
import presentation.component.bonus.ButtonOk
import presentation.component.bonus.Loading
import presentation.component.bottomsheet.ComposeShareInviteBottomSheet
import presentation.component.bottomsheet.ComposeShareInviteMiniBottomSheet
import presentation.component.dialogwindow.NotificationEnableDialog
import presentation.mvvm.InviteViewModel
import ui.BaseGreen
import ui.BaseRed
import ui.Yellow
import ui.bt_disable
import ui.gray_filters
import ui.lottie.loading
import utils.decliningInvait
import utils.isScrolledToTheEnd
import utils.stateRemember

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun InviteInitBottomSheet(navigator: Navigator) {
    val viewModel: InviteViewModel = koinViewModel()

    var isSheetOpen = rememberSaveable { mutableStateOf(false) }
    var isDialogOpen = rememberSaveable { mutableStateOf(false) }
    var removeInvite = InviteCellDom().stateRemember()
    val sheetState = rememberModalBottomSheetState()
    val stateInvite = viewModel.generationInviteFlow.collectAsState(null)


    if (isSheetOpen.value && stateInvite.value != null) {
        ModalBottomSheet(
            //modifier = Modifier.padding(horizontal = 16.dp),
            sheetState = sheetState,
            //scrimColor = Color.White,
            tonalElevation = 8.dp,
            onDismissRequest = {
                isSheetOpen.value = false
            },
        ) {
            if (stateInvite.value != null)
                if (stateInvite.value?.status == StatusInvite.WAIT)
                    ComposeShareInviteBottomSheet(stateInvite.value!!) {
                        removeInvite.value = it
                        isDialogOpen.value = true
                        isSheetOpen.value = false
                    }
                else ComposeShareInviteMiniBottomSheet(stateInvite.value!!)
        }
    }
    InviteInit(viewModel, isSheetOpen)
    if (isDialogOpen.value)
        removeInviteCode(removeInvite, isDialogOpen, isSheetOpen, viewModel)

}

@Composable
fun removeInviteCode(
    shareInvite: MutableState<InviteCellDom>,
    isDialogOpen: MutableState<Boolean>,
    isSheetOpen: MutableState<Boolean>,
    viewModel: InviteViewModel,
) {
    NotificationEnableDialog(data = DialogNotification(
        bt1 = "Удалить",
        bt2 = "Отмена",
        title = "Удалить ${shareInvite.value.code}?",
        description = "Инвайт-код и ссылка на его активацию перестанут работать",

        ), blurClick = {
        isDialogOpen.value = false
        //binding.dialogCompose.slowGone()
    }, onBt1Click = {
        //binding.bottomSheetCompose.blurBottomSheetCompose.performClick()
        viewModel.removeInvite(shareInvite.value.code)
        //binding.dialogCompose.slowGone()
        isDialogOpen.value = false
        isSheetOpen.value = false

    }, onBt2Click = {
        isDialogOpen.value = false
        //binding.dialogCompose.slowGone()
    })
}


@Composable
fun InviteInit(viewModel: InviteViewModel, isSheetOpen: MutableState<Boolean>) {
    // val loading = viewModel.loadingFlow.collectAsStateWithLifecycle(ViewEvent.Loading.HideLoading, lifecycleOwner = requireActivity())
    // val viewModel: InviteViewModel = koinViewModel()
    var refreshing by false.stateRemember()

    val pullRefreshState = rememberPullRefreshState(refreshing, onRefresh = {
        refreshing = true
        viewModel.resetInit()
    })
    Loading(isShow = ViewEvent.Loading.HideLoading, Modifier.pullRefresh(pullRefreshState)) {
        val inviteData = viewModel.dataItems.collectAsState().value
        LaunchedEffect(inviteData) {
            //if (loading.value == ViewEvent.Loading.HideLoading)
            refreshing = false
        }
        when (inviteData) {
            is StateInvite.Data -> InviteList(
                inviteData,
                viewModel = viewModel,
                modifier = Modifier,
                isSheetOpen = isSheetOpen
            )

            StateInvite.Empty -> EmptyInviteList()
            StateInvite.Init -> {}
        }
        if (inviteData !is StateInvite.Init) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                ButtonOk(
                    "Создать инвайт-код".uppercase(),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
                ) {
                    isSheetOpen.value = true
                    viewModel.generationInviteCode()
                }
            }
        }
        PullRefreshIndicator(
            refreshing, pullRefreshState,
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InviteList(
    inviteData: StateInvite.Data,
    modifier: Modifier = Modifier,
    viewModel: InviteViewModel,
    isSheetOpen: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
    ) {
        var isShowTitleBar by false.stateRemember()
        val scrollState = rememberLazyListState()
        val i = scrollState.isScrolledToTheEnd()
        val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(loading))

        ToolbarBase(
            Modifier.padding(horizontal = 0.dp),
            title = "Мои инвайты",
            isHideAnimate = !isShowTitleBar
        ) {
            //findNavController().popBackStack()
        }
        LaunchedEffect(scrollState.firstVisibleItemIndex) {
            isShowTitleBar = scrollState.firstVisibleItemIndex > 0
        }
        LaunchedEffect(i) {
            if (i && viewModel.currentState.hasNextPage) viewModel.nextPage()
        }
        LazyColumn(
            state = scrollState,
        ) {
            items(1) {
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    //style = BaseUi.styleDefault,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    text = "Мои инвайты",
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp)
                )
            }
            items(1) {
                Spacer(modifier = Modifier.size(32.dp))
                Text(
                    //style = BaseUi.styleDefault,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    lineHeight = 26.sp,
                    letterSpacing = 0.02.em,
                    text = "Приглашайте коллег в профессиональное сообщество",
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.size(32.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = if (inviteData.currentStateInvite.maxInvites == 0) "НЕТ ИНВАЙТОВ" else inviteData.currentStateInvite.maxInvites.toString()
                            .decliningInvait().uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        lineHeight = 8.sp,
                        color = bt_disable,
                        letterSpacing = 0.8.sp,
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.CenterStart)
                    )
                    DropdownExample(
                        modifier = Modifier.align(Alignment.TopEnd),
                        inviteData.currentStateInvite.filter,
                        viewModel.currentState.selectedFilter
                    ) {
                        viewModel.selectSort(it)
                    }
                }
            }
            val inviteList = inviteData.invites

            if (inviteList.isEmpty()) {
                items(1) {
                    Spacer(modifier = Modifier.size(32.dp))
                    Image(
                        painter = painterResource(Res.drawable.ic_invite_bar),
                        contentDescription = "back",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 80.dp),
                        contentScale = ContentScale.FillWidth,
                    )
                }
            }
            items(inviteList.count(), key = { inviteList[it].code }) { item ->
                when (inviteList[item].status) {
                    StatusInvite.ACTIVED -> CellInviteActivated(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 32.dp)
                            .animateItemPlacement(),
                        inviteCellDom = inviteList[item]
                    ) {
                        viewModel.infoInvite(inviteList[item].code)
                        isSheetOpen.value = true
                        //ope(inviteList[item])
                    }

                    StatusInvite.WAIT -> CellInviteWait(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 32.dp)
                            .animateItemPlacement(),
                        inviteCellDom = inviteList[item],
                        viewModel = viewModel
                    ) {
                        viewModel.infoInvite(inviteList[item].code)
                        isSheetOpen.value = true
                        //openBottomSheet(inviteList[item])
                    }

                    StatusInvite.EXPIRED -> CellInviteOverdue(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 32.dp)
                            .animateItemPlacement(),
                        inviteCellDom = inviteList[item]
                    ) {
                        viewModel.infoInvite(inviteList[item].code)
                        isSheetOpen.value = true
                        //openMiniBottomSheet(inviteList[item])
                    }

                    StatusInvite.UNKNOW -> {}
                }
                if (inviteList.count() - 1 != item)
                    Divider(
                        color = gray_filters,
                        modifier = Modifier
                            .fillMaxWidth()  //fill the max height
                            .height(5.dp)
                    )
//                    LaunchedEffect(scrollState.isScrollInProgress){
//                        Log.e("visibleItemsInfo", scrollState.isScrollInProgress.toString())
//                        if (item == inviteData.count()-1)
//                            viewModel.nextPage()
//                    }
            }
            if (viewModel.currentState.hasNextPage)
                items(1, key = { "LottieAnimation" }) {
                    LottieAnimation(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 32.dp)
                            .height(132.dp)
                            .fillMaxWidth()
                            .animateItemPlacement(),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                    )
                }
            items(1) {
                Spacer(modifier = Modifier.size(80.dp))
            }
        }
    }
}

@Composable
fun CellInviteWait(
    modifier: Modifier = Modifier,
    inviteCellDom: InviteCellDom = InviteCellDom(),
    viewModel: InviteViewModel,
    onClick: (() -> Unit)? = null
) {
    Column(modifier = modifier
        .background(color = Color.White)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        ) {
            onClick?.invoke()
        }) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            text = inviteCellDom.code.uppercase(),
            modifier = Modifier.wrapContentHeight()
        )
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 0.08.em,
                text = inviteCellDom.status.title.uppercase(),
                modifier = Modifier.wrapContentHeight()
            )
            Text(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                text = "до " + inviteCellDom.dateTimeActiveToText,

                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .align(Alignment.TopEnd)
            )
        }
        Spacer(modifier = Modifier.size(11.dp))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)),
            progress = inviteCellDom.slider,
            color = BaseRed,
            trackColor = gray_filters,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.02.em,
            text = inviteCellDom.statusTime,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        )
    }
}

@Composable
fun CellInviteActivated(
    modifier: Modifier = Modifier,
    inviteCellDom: InviteCellDom = InviteCellDom(),
    onClick: (() -> Unit)? = null
) {
    Column(modifier = modifier
        .background(color = Color.White)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        ) {
            onClick?.invoke()
        }) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = inviteCellDom.code.uppercase(),
                modifier = Modifier.wrapContentHeight()
            )
            Text(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                text = inviteCellDom.dateTimeActiveToText,
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .align(Alignment.CenterEnd)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))

        Row {
            if (inviteCellDom.currentIntTotal > 0) {
                RoundedColoredEmptySpace(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = if (inviteCellDom.currentIntTotal >= inviteCellDom.maxTotal) BaseGreen else Yellow,
                    8.dp
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 0.08.em,
                text = inviteCellDom.status.title.uppercase(),
                modifier = Modifier.wrapContentHeight()
            )
        }
        if (inviteCellDom.message.isNotEmpty()) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                text = inviteCellDom.message,
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
            )
        }
    }
}

@Composable
fun EmptyInviteList(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            ToolbarBase(title = "Мои инвайты", isHideAnimate = true, isHide = true) {
                //findNavController().popBackStack()
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                text = "Мои инвайты",
                modifier = Modifier.wrapContentHeight()
            )
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.02.em,
                text = "Создайте уникальный инвайт-код и приглашайте коллег в профессиональное сообщество",
                modifier = Modifier.wrapContentHeight()
            )
            Spacer(modifier = Modifier.size(32.dp))
            Image(
                painter = painterResource(Res.drawable.ic_invite_bar),
                contentDescription = "back",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 80.dp),
                contentScale = ContentScale.FillWidth,
            )
        }
    }
}

@Composable
fun RoundedColoredEmptySpace(
    modifier: Modifier = Modifier,
    color: Color,
    cornerRadius: Dp = 0.dp,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .padding(bottom = 1.dp)
            .background(color, shape = RoundedCornerShape(cornerRadius))
            .width(8.dp)
            .height(8.dp)
    )
}

@Composable
fun CellInviteOverdue(
    modifier: Modifier = Modifier,
    inviteCellDom: InviteCellDom = InviteCellDom(),
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White.copy(alpha = 0.5f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ) { onClick?.invoke() }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    text = inviteCellDom.code.uppercase(),
                    modifier = Modifier.wrapContentHeight()
                )
                Text(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    text = inviteCellDom.dateTimeActiveToText,
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .align(Alignment.CenterEnd)
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 0.08.em,
                text = inviteCellDom.status.title.uppercase(),
                modifier = Modifier.wrapContentHeight()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .alpha(0.7f)
                .background(color = Color.White)
        )
    }
}
