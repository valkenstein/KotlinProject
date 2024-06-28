package network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class InviteResponse {
    @SerialName("navigation")
    val navigation: NavigationResponse? = null

    @SerialName("inviteCodes")
    val inviteCodes: List<InviteItemResponse>? = emptyList()

    @SerialName("inviteCode")
    val inviteCode: InviteItemResponse? = null

    @SerialName("statuses")
    val statuses: Map<String, String>? = null
}
@Serializable
data class NavigationResponse(
    @SerialName("nextPage") val nextPage: String?,
    @SerialName("prevPage") val prevPage: String?,
    @SerialName("limit") val limit: String?,
    @SerialName("recordCount") val recordCount: String?,
    @SerialName("currentPage") val currentPage: String?,
    @SerialName("pageCount") val pageCount: String?,
)

class InviteCountResponse {
    @SerialName("count")
    val count: Int? = null
}
@Serializable
class InviteItemResponse(
    @SerialName("id") val id: String?,
    @SerialName("code") val code: String?,
    @SerialName("status") val status: String?,
    @SerialName("dateTimeActiveToText") val dateTimeActiveToText: String?,
    @SerialName("dateTimeActivatedText") val dateTimeActivatedText: String?,
    @SerialName("dateTimeCreateText") val dateTimeCreateText: String?,
    @SerialName("currentTotal") val currentTotal: String?,
    @SerialName("currentIntTotal") val currentIntTotal: Int?,
    @SerialName("customerInfo") val customerInfo: String?,
    @SerialName("maxTotal") val maxTotal: Int?,
    @SerialName("message") val message: String?,
    @SerialName("linkActivate") val linkActivate: String?,
    @SerialName("statusTime") val statusTime: StatusTimeResponse?,
)
@Serializable
data class StatusTimeResponse(
    @SerialName("day") val day: String?,
    @SerialName("hours") val hours: String?,
    @SerialName("minutes") val minutes: String?,
    @SerialName("seconds") val seconds: String?
)
