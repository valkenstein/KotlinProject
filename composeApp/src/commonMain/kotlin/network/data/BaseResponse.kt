package network.data

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse<Response>(
    val data: Response?,
    val success: Boolean? = null,
    val message: String?,
    val errorId: String? = null,
)
