package domain.model

import randomUUID


data class InviteCell(
    val list: List<InviteCellDom> = listOf(
        InviteCellDom(),
        InviteCellDom(),
        InviteCellDom(),
        InviteCellDom()
    ),
)

data class CountInviteListDom(
    val count: Int = 0,
)

data class InviteListDom(
    val navigation: NavigationDom = NavigationDom(1, 1, 1, 1, 1, 1),
    val inviteCodes: List<InviteCellDom> = emptyList(),
    val statuses: List<MapKey> = listOf(),
)

data class NavigationDom(
    val nextPage: Int = 0,
    val prevPage: Int = 0,
    val limit: Int = 0,
    val recordCount: Int = 0,
    var currentPage: Int = 0,
    var pageCount: Int = 0,
)


data class MapKey(
    val key: String = "",
    val value: String = "все",
    val img: String = "",
)

data class InviteCellDom(
    val id: String = randomUUID(),
    val code: String = "RH2505",
    val status: StatusInvite = StatusInvite.WAIT,
    val dateTimeActiveToText: String = "до 19 августа, 23:15",
    val dateTimeActivatedText: String = "до 19 августа, 23:15",
    val dateTimeCreateText: String = "Осталось 2 дня 14 ч 15 мин",
    val statusTime: String = "Осталось 2 дня 14 ч 15 мин",
    val linkActivate: String = "",
    val message: String = "",
    val maxTotal: Int = 0,
    val currentTotal: String = "",
    val currentIntTotal: Int = 0,
    val customerInfo: List<BoldTypeCustomerInfo> = emptyList(),
    val slider: Float = 0f,
    val sliderPrice: Float = 0f,
)

data class BoldTypeCustomerInfo(
    val bold: Boolean = false,
    val text: String
)

enum class StatusInvite(val title: String) {
    ACTIVED("активирован"),
    WAIT("ожидает активации"),
    EXPIRED("просрочен"),
    UNKNOW("все")
}