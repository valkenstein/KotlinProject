package network.repository

import network.api.IApiHomeService

class ContentHomeRepository   constructor(
    private val apiHomeService: IApiHomeService,
) {

//    suspend fun getCatalog() = apiHomeService.getCatalog()
//    suspend fun getBrands() = apiHomeService.getBrands()
    suspend fun getAccumulativeBrands() = apiHomeService.getAccumulativeBrands()
    suspend fun getInfoAccumulativeStatus() = apiHomeService.getInfoAccumulativeStatus()
//    suspend fun getShowcase() = apiHomeService.getShowcase()
//    suspend fun getProperties() = apiHomeService.getProperties()
}