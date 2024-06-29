package network.data

import kotlinx.serialization.Serializable

@Serializable
data class EmptyResponse( val text: String? = null)