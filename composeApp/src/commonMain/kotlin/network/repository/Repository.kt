package network.repository

import network.api.NetworkApi

class Repository(private val api: NetworkApi) {
    suspend fun getListData() = api.getBonusOperation()
}