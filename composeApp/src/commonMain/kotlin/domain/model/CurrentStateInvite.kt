package domain.model


sealed class StateInvite() {
    data object Init : StateInvite()
    data object Empty : StateInvite()
    data class Data(
        val currentStateInvite: CurrentStateInvite,
        val invites: List<InviteCellDom>
    ) : StateInvite()

}

data class CurrentStateInvite(
    var page: Int = 1,
    var maxPage: Int = 1,
    var maxInvites: Int = 1,
    var hasNextPage: Boolean = false,
    var filter: List<MapKey> = emptyList(),
    var selectedFilter: MapKey = MapKey(),
) {
    fun reset() {
        page = 0
        selectedFilter = MapKey()
    }

    fun selectPage(i: Int) {
        page = i
    }

//    fun selectFilter(sort: String) {
//        sortTitle = sort
//        this.sort = when (sort) {
//            "Ожидают активации" -> StatusInviteState.WAIT
//            "Активированные" -> StatusInviteState.ACTIVED
//            "Активированные с покупками" -> StatusInviteState.ACTIVED_PRODUCTS
//            "Просроченные" -> StatusInviteState.EXPIRED
//            else -> StatusInviteState.ALL
//        }
//    }
}

enum class StatusInviteState(val title: String) {
    WAIT("wait_activated"),
    ACTIVED("activated"),
    ACTIVED_PRODUCTS("wait_activated"),
    EXPIRED("expired"),
    ALL("")
}