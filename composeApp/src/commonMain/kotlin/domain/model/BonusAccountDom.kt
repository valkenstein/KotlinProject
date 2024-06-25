package domain.model


class BonusAccountDom(
    val bonusAccounts: List<BonusAccountItemDom>
)

class BonusAccountItemDom(
    val date: String,
    val text: String,
    val bonus: String,
    val type: String,
)