package com.simrussa.remote.models.home

 
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import network.data.AccumulativeResponse

@Serializable
data class HomeResponse(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String?,
    @SerialName("url") val url: String?,
    @SerialName("count_products") val countProducts: String?,
    @SerialName("country") val country: String?,
    @SerialName("favorite") val favorite: Boolean?,
    @SerialName("img") val img: String?,
    @SerialName("img_mini") val img_mini: String?,
    @SerialName("img_big") val img_big: String?,
    @SerialName("logo") val logo: String?,
    @SerialName("discounts") val discounts: Map<String, String>?,
    @SerialName("filter") val filter: List<FilterResponse?>?,
    @SerialName("include_sections") val includeSections: Map<String, HomeResponse>?
)
@Serializable
data class FilterResponse(
    @SerialName("id") val id: Int,
    @SerialName("code") val code: String,
    @SerialName("name") val name: String
)
@Serializable
data class CategoriesResponse(
    @SerialName("items") val items: List<HomeResponse>?,
)
@Serializable
data class StatusesResponse(
    @SerialName("statuses") val statuses: List<AccumulativeResponse>?,
)


data class DiscountsResponse(
    @SerialName("base") val base: String?,
    @SerialName("silver") val silver: String?,
    @SerialName("gold") val gold: String?,
    @SerialName("platinum") val platinum: String?,
)



