package presentation.mvvm

import domain.model.CurrentStateInvite
import domain.model.InviteCellDom
import domain.model.MapKey
import domain.model.NavigationDom
import domain.model.StateInvite
import domain.model.StatusInvite
import domain.usecase.GenerationInviteCodeUseCase
import domain.usecase.GetInviteListUseCase
import domain.usecase.InformationInviteUseCase
import domain.usecase.RemoveInviteUseCase
import domain.usecase.RestoreInviteCodeUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent


class InviteViewModel constructor(
    private val getInviteListUseCase: GetInviteListUseCase,
    private val removeInviteUseCase: RemoveInviteUseCase,
    private val informationInviteUseCase: InformationInviteUseCase,
    private val generationInviteCodeUseCase: GenerationInviteCodeUseCase,
    private val restoreInviteCodeUseCase: RestoreInviteCodeUseCase,
) : BaseViewModel(), KoinComponent {
    private val _dataItems: MutableStateFlow<StateInvite> = MutableStateFlow(StateInvite.Init)
    val dataItems: StateFlow<StateInvite> get() = _dataItems.asStateFlow()

    private val _generationInviteFlow = MutableSharedFlow<InviteCellDom>()
    val generationInviteFlow = _generationInviteFlow.asSharedFlow()
    val currentState = CurrentStateInvite()
    fun init() {
        launchInVMScope {
            showLoading()
            currentState.selectPage(1)
            //currentState.reset()
            getInviteListUseCase(
                currentState.page,
                currentState.selectedFilter.key
            ).collectSuccess {
                if (it.inviteCodes.isEmpty())
                    _dataItems.emit(StateInvite.Empty)
                else _dataItems.emit(StateInvite.Data(currentState, it.inviteCodes))
                currentState.filter = it.statuses
                updatePage(it.navigation)
            }
        }
    }

    fun resetInit() {
        launchInVMScope {
            showLoading()
            currentState.selectPage(1)
            getInviteListUseCase(
                currentState.page,
                currentState.selectedFilter.key
            ).collectSuccess {
                currentState.filter = it.statuses
                updatePage(it.navigation)
                _dataItems.emit(StateInvite.Data(currentState, it.inviteCodes))
            }
        }
    }


    fun infoInvite(code: String) {
        runVibration()
        launchInVMScope {
            informationInviteUseCase(code).collectSuccess {
                _generationInviteFlow.emit(it)
            }
        }
    }

    private fun updatePage(navigation: NavigationDom) {
        currentState.page = navigation.currentPage
        currentState.maxPage = navigation.pageCount
        currentState.maxInvites = navigation.recordCount
        currentState.hasNextPage = navigation.currentPage < navigation.pageCount
    }

    fun selectSort(sort: MapKey) {
        currentState.selectPage(1)
        currentState.selectedFilter = sort
        launchInVMScope {
            showLoading()
            getInviteListUseCase(currentState.page, sort.key).collectSuccess {
                _dataItems.emit(StateInvite.Data(currentState, it.inviteCodes))
                updatePage(it.navigation)
            }
        }
    }

    fun nextPage() {
        currentState.selectPage(currentState.page + 1)
        launchInVMScope {
            getInviteListUseCase(
                currentState.page,
                currentState.selectedFilter.key
            ).collectSuccess {
                if (_dataItems.value is StateInvite.Data) {
                    val list = (_dataItems.value as StateInvite.Data).invites?.plus(it.inviteCodes)
                        ?: emptyList()
                    _dataItems.emit(StateInvite.Data(currentState, list))
                }
                updatePage(it.navigation)
            }
        }
    }

    fun generationInviteCode() {
        launchInVMScope {
            showLoading()
            generationInviteCodeUseCase().collectSuccess {
                // if (_dataItems.value is StateInvite.Data && (_dataItems.value as StateInvite.Data).invites.isEmpty())
                init()
                _generationInviteFlow.emit(it)
            }
        }
    }

    fun restoreInviteCode(code: String) {
        launchInVMScope {
            showLoading()
            restoreInviteCodeUseCase(code).collectSuccess { restoreInvite ->
                if (_dataItems.value is StateInvite.Data) {
                    val invites = (_dataItems.value as StateInvite.Data).invites.toMutableList()
                    invites.indexOfFirst { it.code == code }.let {
                        if (it != -1) {
                            invites[it] = restoreInvite
                        }
                    }
                    val invite =
                        if (currentState.selectedFilter.value.uppercase() == StatusInvite.UNKNOW.title.uppercase()) invites else invites.filter { it.status.title.uppercase() == currentState.selectedFilter.value.uppercase() }
                    _dataItems.emit(StateInvite.Data(currentState, invite))
                    currentState.maxInvites =
                        currentState.maxInvites - (invites.count() - invite.count())
                }
                _generationInviteFlow.emit(restoreInvite)
            }
        }
    }

    fun removeInvite(code: String) {
        launchInVMScope {
            showLoading()
            removeInviteUseCase(code).collectSuccess {
                resetInit()
            }
        }
    }
}