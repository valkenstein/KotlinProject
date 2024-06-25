package network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BonusInfoResponse(
    @SerialName("bonusInfo") val bonusOperations: BonusOperationsResponse?,
)
@Serializable
data class BonusOperationsResponse(
    @SerialName("isBonus") val isBonus: String?,
    @SerialName("count") val count: String?,
    @SerialName("date") val date: String?,
    @SerialName("operations") val operations: List<OperationsResponse>?,
)
@Serializable
data class OperationsResponse(
    @SerialName("id") val id: String?,
    @SerialName("date") val date: String?,
    @SerialName("title") val title: String?,
    @SerialName("typeValue") val typeValue: String?,
    @SerialName("value") val value: String?,
)