package network.api


import com.simrussa.remote.models.home.HomeResponse
import de.jensklingenberg.ktorfit.http.GET
import network.data.BaseResponse
import network.data.StatusesResponse
import kotlin.jvm.JvmSuppressWildcards

interface IApiHomeService {
    //    @GET("v2/catalog/getSections")
//    @JvmSuppressWildcards
//    suspend fun getCatalog(): Result<BaseResponse<CategoriesResponse>>
//
//    @GET("catalog/brands")
//    @JvmSuppressWildcards
//    suspend fun getBrands(): Result<BaseResponse<List<HomeResponse>>>
//
    @GET("cabinet/getAccumulativeBrands")
    @JvmSuppressWildcards
    suspend fun getAccumulativeBrands(): BaseResponse<List<HomeResponse>>


    @GET("cabinet/getInfoAccumulativeStatus")
    @JvmSuppressWildcards
    suspend fun getInfoAccumulativeStatus(): BaseResponse<StatusesResponse>


//    @GET("catalog/properties")
//    @JvmSuppressWildcards
//    suspend fun getProperties(): Result<BaseResponse<HashMap<String, PropertiesResponse>>>
//
//
//    @GET("showcase/list")
//    @JvmSuppressWildcards
//    suspend fun getShowcase(): Result<BaseResponse<ShowcaseResponse>>
}