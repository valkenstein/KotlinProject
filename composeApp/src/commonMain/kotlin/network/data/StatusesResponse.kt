package network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusesResponse(
    @SerialName("statuses") val statuses: List<AccumulativeResponse>?,
)
@Serializable
data class AccumulativeResponse(
    @SerialName("id") val id: String?,
    @SerialName("title") val title: String?,
    @SerialName("sumFrom") val sumFrom: String?,
    @SerialName("image") val image: String?,
)