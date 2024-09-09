package domain.model


data class CategoryDom(
    val id: String,
    val idParent: String = "",
    val title: String,
    val link: String = "",
    var src: Int = 0,
    var srcBig: Int = 0,
    var includeSections: List<CategoryDom> = emptyList(),
    val url: String = "",
    val img: String = "",
    val logo: String = "",
    val discounts: Map<String, String> = emptyMap(),
    var favoriteSort: Boolean = false,
    var favorite: Boolean = false,
    var rootSection: Boolean = false,
)

data class DiscountsDom(
    val base: String = "",
    val silver: String = "",
    val gold: String = "",
    val platinum: String = "",
)