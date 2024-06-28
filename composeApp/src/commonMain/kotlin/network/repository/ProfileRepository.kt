package network.repository

import network.api.IAuthorizationApiService

class ProfileRepository  constructor(
    private val authorizationApiService: IAuthorizationApiService,
) {
    suspend fun generationInviteCode() = authorizationApiService.generationInviteCode()
    suspend fun restoreInviteCode(code:String) = authorizationApiService.restoreInviteCode(code)
    suspend fun getCountInviteListWait() = authorizationApiService.getCountInviteListWait()
    suspend fun getInviteList(page: Int, sort: String) = authorizationApiService.getInviteList(page, sort)
    suspend fun removeInvite(code: String) = authorizationApiService.removeInvite(code)
    suspend fun informationInvite(code: String) = authorizationApiService.informationInvite(code)
    suspend fun activateInvite(code: String) = authorizationApiService.activateInvite(code)

    suspend fun cancelOrder(orderId: String) = authorizationApiService.cancelOrder(orderId)
    suspend fun replayOrder(orderId: String) = authorizationApiService.replayOrder(orderId)
    suspend fun removeProfile() = authorizationApiService.removeProfile()

    suspend fun updateUserProfile(city: Map<String, String>) = authorizationApiService.updateUserProfile(city)
    suspend fun addBonusProgram(city: Map<String, String>) = authorizationApiService.addBonusProgram(city)

    suspend fun getInfoPayAndDelivery()= authorizationApiService.getInfoPayAndDelivery()

}