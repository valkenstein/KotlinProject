import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import dataBase.getPeopleDatabase
import presentation.component.AppProject

fun MainViewController() = ComposeUIViewController {
    //App()
    val dao = remember {
        getPeopleDatabase().peopleDao()
    }
    AppProject(false, false)
}