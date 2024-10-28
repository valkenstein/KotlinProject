import androidx.compose.ui.window.ComposeUIViewController
import presentation.component.AppProject

fun MainViewController() = ComposeUIViewController {
//    val dao = remember {
//        getPeopleDatabase().peopleDao()
//    }

    AppProject(false, false)
}