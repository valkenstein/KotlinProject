package network.api


import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import de.jensklingenberg.ktorfit.http.QueryMap
import network.data.BaseResponse
import network.data.EmptyResponse
import network.data.InviteCountResponse
import network.data.InviteResponse
import kotlin.jvm.JvmSuppressWildcards

interface IAuthorizationApiService {
    @GET("system/notification/sms/send")
    @JvmSuppressWildcards
    suspend fun sendPhone(@Query("phone") phone: String): BaseResponse<Map<Any, Any>>

    @GET("cabinet/userPhoneUpdate")
    @JvmSuppressWildcards
    suspend fun changePhone(
        @Query("oldPhone") oldPhone: String,
        @Query("newPhone") newPhone: String
    ): BaseResponse<Map<Any, Any>>


    @GET("system/notification/sms/check")
    @JvmSuppressWildcards
    suspend fun authorization(
        @Query("phone") phone: String,
        @Query("code") code: String
    ): BaseResponse<Map<Any, Any>>



//    @GET("user/device_token/add")
//    suspend fun sendTokenFireBase(
//        @Query("user_id") userId: String,
//        @Query("token") token: String,
//        @Query("device") device: String = "android"
//    ): BaseResponse<EmptyResponse>>


    @GET("invite/generateOneCode")
    suspend fun generationInviteCode(): BaseResponse<InviteResponse>

    @GET("invite/activateInviteCode")
    suspend fun restoreInviteCode(@Query("code") code: String): BaseResponse<InviteResponse>


    @GET("invite/infoInviteCode")
    suspend fun informationInvite(@Query("code") code: String): BaseResponse<InviteResponse>
    @GET("invite/infoInviteCode")
    suspend fun activateInvite(@Query("code") code: String): BaseResponse<InviteResponse>
    @GET("invite/removeInviteCode")
    suspend fun removeInvite(@Query("code") code: String): BaseResponse<EmptyResponse>
    @GET("invite/countWaitActiveInvite")
    suspend fun getCountInviteListWait(): BaseResponse<InviteCountResponse>

    @GET("invite/listInviteCode")
    suspend fun getInviteList( @Query("navigation[page]")  sort: Int , @Query("filter[status]")  page: String): BaseResponse<InviteResponse>


    @GET("cabinet/cancelOrder")
    suspend fun cancelOrder(@Query("orderId") orderId: String): BaseResponse<EmptyResponse>

    @GET("cabinet/replyOrder")
    suspend fun replayOrder(@Query("orderId") orderId: String): BaseResponse<EmptyResponse>

    @GET("cabinet/userProfileDelete")
    suspend fun removeProfile(): BaseResponse<EmptyResponse>


    @GET("city/addCityOnlyUser")
    suspend fun changeCity(
        @Query("city") query: String,
        @Query("user_id") userId: String
    ): BaseResponse<EmptyResponse>

    
    @POST("cabinet/userProfileUpdate")
    @JvmSuppressWildcards
    suspend fun updateUserProfile(@QueryMap param: Map<String, String>): BaseResponse<Map<Any, Any>>

    @POST("user/addBonusProgram")
    @JvmSuppressWildcards
    suspend fun addBonusProgram(@QueryMap param: Map<String, String>): BaseResponse<Map<Any, Any>>

    @GET("contacts/getDeliveryPay")
    suspend fun getInfoPayAndDelivery(): BaseResponse<EmptyResponse>
}