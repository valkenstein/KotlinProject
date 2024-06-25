package presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import network.di.KoinInjector
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.mvvm.ContactListViewModel
import presentation.ProjectTheme

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

        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ContactListScreen(
                state = state,
                newConcat = viewModel.newContact,
                onEvent = viewModel::onEvent
            )

        }
    }
    //}
}