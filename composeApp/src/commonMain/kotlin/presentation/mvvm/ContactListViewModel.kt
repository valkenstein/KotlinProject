package presentation.mvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import domain.BonusOperationUseCase
import domain.model.ContactDom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.ContactListEvent
import presentation.ContactListState

class ContactListViewModel(private val bonusOperationUseCase: BonusOperationUseCase) :
    androidx.lifecycle.ViewModel(),
    KoinComponent {
    private val _state = MutableStateFlow(
        ContactListState(
            contacts
        )
    )

    val state = _state.asStateFlow()
    var newContact: ContactDom? by mutableStateOf(null)
        private set

    init {
        getBonus()
    }

    private fun getBonus() {
        viewModelScope.launch {
            bonusOperationUseCase("sd").collect {
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