package presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import presentation.ProjectTheme
import presentation.component.invite.InviteInitBottomSheet
import presentation.component.bonus.LazyBonusAccounts
import presentation.mvvm.ContactListViewModel

@Composable
fun AppProject(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
//    KoinApplication(application = {
//            KoinInjector.koinApp.koin
//        }) {
    ProjectTheme(
        darkTheme,
        dynamicColor
    ) {
//

        InviteInitBottomSheet()


        //BonusInit()
//        val viewModel = getViewModel(
//            key = "contact-list",
//            factory = viewModelFactory {
//                ContactListViewModel()
//            }
//        )




//        val state by viewModel.state.collectAsState()
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            ContactListScreen(
//                state = state,
//                newConcat = viewModel.newContact,
//                onEvent = viewModel::onEvent
//            )
//
//        }
    }
    //}
}

@Composable
fun BonusInit(){
    val viewModel: ContactListViewModel = koinViewModel()
    val bonusOperation by viewModel.bonusState.collectAsState()
    Column(Modifier.fillMaxSize()) {
        ToolbarBase(Modifier.padding(16.dp), title = "Бонусный счет") {
            //zfindNavController().popBackStack()
        }
        LazyBonusAccounts(
            bonusOperation.count().toString(),
            bonusOperation,
            Modifier.weight(1.0f)
        )
    }
}