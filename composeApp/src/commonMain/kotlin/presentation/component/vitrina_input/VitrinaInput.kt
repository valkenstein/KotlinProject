package presentation.component.vitrina_input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import domain.model.TypeEdit
import domain.model.UserOrderDom
import domain.model.ViewEvent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.ToolbarBase
import presentation.component.bonus.ButtonOk
import presentation.component.bonus.Loading
import presentation.component.level_info.InputText2
import presentation.component.switch_component.SwitchCustom
import presentation.mvvm.LevelInformationViewModel
import presentation.mvvm.SupplementViewModel
import ui.BaseRed
import utils.stateRemember
import utils.validationField

@Composable
fun InitViewVitrinaInput() {
    val viewModel: SupplementViewModel = koinViewModel()
    val loading =
        viewModel.loadingFlow.collectAsState(ViewEvent.Loading.InitialEvent)
    Loading(isShow = loading.value) {
        Content(UserOrderDom())
    }
}

@Preview
@Composable
fun Content(user: UserOrderDom) {
    val viewModel: SupplementViewModel = koinViewModel()
    var stateDisableButton by true.stateRemember()
    val infoValidationField by mutableMapOf(
        0 to user.lastName.isNotEmpty(),
        1 to user.firstName.isNotEmpty(),
        2 to true,
        3 to user.email.isNotEmpty(),
    ).stateRemember()
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            ToolbarBase(Modifier.padding(0.dp), title = "", isHideAnimate = true, isHide = true) {
                //findNavController().popBackStack()
            }
            Column(
                Modifier
                    .padding(start =16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxSize()
                    .imePadding()
                    .verticalScroll(scrollState)
            ) {

                //region Description


                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "Заполните \nсвой профиль",
                    fontWeight = FontWeight.Bold,
                    lineHeight = 26.sp,
                    fontSize = 28.sp,
                    //  modifier = Modifier.align(Alignment.CenterStart)
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "Мы должны знать своих!",
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    letterSpacing = 0.02.em,
                    //  modifier = Modifier.align(Alignment.CenterStart)
                )
                Spacer(modifier = Modifier.size(52.dp))
//endregion
                InputText2(text = user.lastName, hint = "Фамилия *", onValidationChange = {
                    val v = it.validationField(TypeEdit.TEXT, true)
                    infoValidationField[0] = v.success
                    stateDisableButton = infoValidationField.containsValue(false)
                    user.lastName = it
                    v.messageError
                })
                Spacer(modifier = Modifier.size(16.dp))

                InputText2(text = user.firstName, hint = "Имя *", onValidationChange = {
                    val v = it.validationField(TypeEdit.TEXT, true)
                    infoValidationField[1] = v.success
                    stateDisableButton = infoValidationField.containsValue(false)
                    user.firstName = it
                    v.messageError
                })
                Spacer(modifier = Modifier.size(16.dp))

                InputText2(text = user.secondName, hint = "Отчество", onValidationChange = {
                    val v = it.validationField(TypeEdit.TEXT, false)
                    infoValidationField[2] = v.success
                    stateDisableButton = infoValidationField.containsValue(false)
                    user.secondName = it
                    v.messageError
                })
                Spacer(modifier = Modifier.size(16.dp))

                InputText2(text = user.email, hint = "Email *", onValidationChange = {
                    val v = it.validationField(TypeEdit.EMAIL, true)
                    infoValidationField[3] = v.success
                    stateDisableButton = infoValidationField.containsValue(false)
                    user.email = it
                    v.messageError
                })
                Spacer(modifier = Modifier.size(20.dp))
                SwitchCustom(
                    Modifier,
                    text = "Присылать мне почтовые рассылки со специальными предложениями",
                    user.isSubscribe
                ) {
                    user.isSubscribe = it
                }
                Spacer(modifier = Modifier.size(39.dp))

                Text(
                    modifier = Modifier.clickable {
                        //viewModel.openWeb(WebViewFragment.CONF)
                    },
                    text = buildAnnotatedString {
                        append("Нажимая на кнопку «Сохранить» вы соглашаетесь с ")
                        withStyle(style = SpanStyle(color = BaseRed)) {
                            append("политикой конфиденциальности")
                        }
                    },
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    letterSpacing = 0.02.em,
                    lineHeight = 19.sp,
                )
                Spacer(modifier = Modifier.size(140.dp))
            }
        }
        ButtonOk(
            "Сохранить".uppercase(),
            isDisable = stateDisableButton,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
                .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
        ) {
            viewModel.safe2()
        }
    }
}