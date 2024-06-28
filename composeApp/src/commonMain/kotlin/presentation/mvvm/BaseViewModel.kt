package presentation.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.base.asFailure
import domain.base.asSuccess
import domain.base.isSuccess
import domain.model.ResultDom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : androidx.lifecycle.ViewModel() {
    protected fun launchInVMScope(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context = context, block = block)
    }

    protected fun launchMain(
        context: CoroutineContext = Dispatchers.Main,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context = context, block = block)
    }

    suspend fun showLoading() {
    }

    suspend fun hideLoading() {
    }

    fun runVibration() {
    }

    protected suspend inline fun <T> Flow<ResultDom<T>>.collectSuccess(
        hideLoad: Boolean = true,
        noinline error: (suspend (value: ResultDom.Failure<*>) -> Unit)? = null,
        crossinline action: suspend (value: T) -> Unit,
    ) {
        collect { result ->
            if (result.isSuccess()) {
                if (hideLoad) {
                    hideLoading()
                }
                when (result.asSuccess()) {
                    is ResultDom.Success.Empty -> {
                        launchMain {
                            action(Any() as T)
                        }
                    }

                    else -> {
                        with(result.asSuccess()) {
                            launchMain {
                                action(value)
                            }
                        }
                    }
                }
            } else {
                hideLoading()
                error?.invoke(result.asFailure())
                processError(result.asFailure())
            }
        }
    }

    fun processError(
        error: ResultDom.Failure<out Throwable>,
        showAlert: Boolean = true
    ) {
        //Timber.e(error.error)
        launchInVMScope {
            hideLoading()
            if (showAlert) {
                try {
//                    val params = createAlertForError(error)
//                    mainInteractor.setAlert(params)
                    //alertsSharedFlow.emit(params)
                } catch (e: Exception) {
                    //ignore
                }
            }
        }
    }
}