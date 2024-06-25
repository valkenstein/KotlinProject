import androidx.compose.ui.window.ComposeUIViewController
import presentation.component.AppProject

fun MainViewController() = ComposeUIViewController {
    //App()
    AppProject(false, false)
}