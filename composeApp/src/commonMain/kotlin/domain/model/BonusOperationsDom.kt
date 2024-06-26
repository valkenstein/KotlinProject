package domain.model

data class BonusOperationsDom(
    val isBonus: Boolean,
    val count: Int,
    val date: String,
    val operations: List<BonusAccountItemDom>,
)