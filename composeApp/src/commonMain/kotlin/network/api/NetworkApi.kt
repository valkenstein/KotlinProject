package network.api

import de.jensklingenberg.ktorfit.http.GET
import network.data.BaseResponse
import network.data.BonusInfoResponse

interface NetworkApi {
    @GET("cabinet/getBonusOperation")
    suspend fun getBonusOperation():  BaseResponse<BonusInfoResponse>
}