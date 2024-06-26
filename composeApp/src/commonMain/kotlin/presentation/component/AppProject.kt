package presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import network.di.KoinInjector
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.mvvm.ContactListViewModel
import presentation.ProjectTheme
import presentation.component.bonus.LazyBonusAccounts

@OptIn(KoinExperimentalAPI::class)
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
        val viewModel: ContactListViewModel = koinViewModel()
//        val viewModel = getViewModel(
//            key = "contact-list",
//            factory = viewModelFactory {
//                ContactListViewModel()
//            }
//        )
        val bonusOperation by viewModel.bonusState.collectAsState()
        // val bonusOperation by viewModel.dataItems.collectAsStateWithLifecycle( lifecycleOwner = viewLifecycleOwner)

        Column(Modifier.fillMaxSize()) {
            ToolbarBase(Modifier.padding(16.dp), title = "Бонусный счет") {
                //findNavController().popBackStack()
            }
            LazyBonusAccounts(
                bonusOperation.count().toString(),
                bonusOperation,
                Modifier.weight(1.0f)
            )
            // Spacer(modifier = Modifier.height(56.dp))
        }

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