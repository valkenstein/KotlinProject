package domain.model

import randomUUID


class BonusAccountDom(
    val bonusAccounts: List<BonusAccountItemDom>
)

class BonusAccountItemDom(
    val id: String = randomUUID(),
    val date: String,
    val text: String,
    val bonus: String,
    val type: String,
)