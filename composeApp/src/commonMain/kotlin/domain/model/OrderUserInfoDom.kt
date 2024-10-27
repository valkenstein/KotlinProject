package domain.model


data class OrderUserInfoDom(
    val city: String,
    val isConfirmOrderPhone: Boolean,
    val delivery: List<DeliveryDom>,
    val user: UserOrderDom?,
    val userDefault: UserOrderDom?,
    val bonus: BonusOrderDom,
    val payments: List<PaymentsDom>,
    val userProfiles: List<UserOrderDom>,
    val addressProfiles: List<AddressOrderDom>,
) {
    fun resetAddress() {
        addressProfiles.forEach { it.selected = false }
    }

    fun hasUser(): Boolean {
        return user?.hasAccount ?: false
    }

    fun isDataFilled(): Boolean {
        return user?.firstName?.isNotEmpty() == true && user.lastName.isNotEmpty() && user.email.isNotEmpty()
    }
}



data class DeliveryDom(
    val id: String,
    val title: String,
    val subtitle: String,
    val code: TypeAddress,
)

data class PaymentsDom(
    val id: String,
    val name: String,
    val text: String,
    val lastUse: Boolean,
    val buttonName: String,
    val images: Map<String, String>,
)

data class UserOrderDom(
    var id: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var secondName: String = "",
    val currentUser: Boolean = false,
    var email: String = "",
    var phone: String = "",
    var hasAccount: Boolean = false,
    var selected: Boolean = false,
    var isSubscribe: Boolean = false,
    var type: TypeUserChange = TypeUserChange.CHANGE,
)  {
    fun checkFullDataComplete(): Boolean =
        (firstName.isNotEmpty() && lastName.isNotEmpty() && if (id == "0") email.isNotEmpty() else phone.isNotEmpty())

    fun checkFullEmptyData() =
        firstName.isEmpty() && lastName.isEmpty() && secondName.isEmpty() && email.isEmpty()
}

enum class TypeUserChange() {
    CHANGE, SUPPLEMENT
}

data class BonusOrderDom(
    val isBonuses: Boolean,
    val total: String,
    val percentBonus: String,
    val joinBonus: Int,
) {
    fun isEmpty() = total == "0" || total.isEmpty()
}

data class AddressOrderDom(
    var id: String = "",
    var type: TypeAddress = TypeAddress.COURIER,
    var active: Boolean = false,
    var city: String = "",
    var region: String = "",
    var pointCode: String = "",
    var street: String = "",
    var flat: String = "",
    var comment: String = "",
    var selected: Boolean = false
)   {
    companion object {
        fun type(s: String): TypeAddress {
            return if (s == "courier") TypeAddress.COURIER else TypeAddress.PICKPOINT
        }
    }
}

enum class TypeAddress(val type: String)  {
    COURIER("courier"), PICKPOINT("pickup")
}
