package presentation.component.bonus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.model.ViewEvent

@Composable
fun Loading(
    isShow: ViewEvent.Loading,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        content.invoke(this)
//        AndroidViewBinding(LoadingContainerBinding::inflate) {
//            root.visibility =
//                if (isShow == ViewEvent.Loading.ShowLoading) View.VISIBLE else View.GONE
//        }
    }
}