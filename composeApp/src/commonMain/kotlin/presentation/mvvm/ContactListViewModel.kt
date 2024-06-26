package presentation.mvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import domain.usecase.BonusOperationUseCase
import domain.model.BonusAccountItemDom
import domain.model.ContactDom
import domain.model.ResultDom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.ContactListEvent
import presentation.ContactListState

class ContactListViewModel(private val bonusOperationUseCase: BonusOperationUseCase) :
    androidx.lifecycle.ViewModel(),
    KoinComponent {
    private val _state = MutableStateFlow(ContactListState(contacts))

    val state = _state.asStateFlow()

    private val _bonusState = MutableStateFlow<List<BonusAccountItemDom>>(emptyList())

    val bonusState = _bonusState.asStateFlow()
    var newContact: ContactDom? by mutableStateOf(null)
        private set

    init {
        getBonus()
    }

    private fun getBonus() {
        viewModelScope.launch {
            bonusOperationUseCase("sd").collect {
                when (it) {
                    is ResultDom.Success.Value -> {
                        _bonusState.emit(it.value.operations)
                    }

                    else -> {

                    }
                }
                println(it)
            }
        }
    }

    fun onEvent(event: ContactListEvent) {

    }
}

val contacts = (0..50).map {
    ContactDom(
        id = it.toLong(),
        firstName = it.toString(),
        lastName = it.toString(),
        phone = it.toString(),
        email = it.toString(),
        null
    )
}