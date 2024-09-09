package domain.mapper

import domain.model.CategoryDom
import com.simrussa.remote.models.home.HomeResponse
import domain.mapper.base.ResultMapper

class HomeMapper constructor(
) : ResultMapper<List<HomeResponse>, List<CategoryDom>>() {
    public override fun mapSuccessResult(src: List<HomeResponse>?): List<CategoryDom> {
        return src?.map { map(it) } ?: emptyList()
    }

    fun map(it: HomeResponse?) =
        CategoryDom(
            id = it?.id ?: "0",
            idParent = it?.id ?: "0",
            title = it?.name ?: "",
            link = it?.img ?: it?.img_mini ?: "",
            discounts = it?.discounts ?: emptyMap(),
            src = 0,
            includeSections = mapSuccessResult(it?.includeSections?.values?.toList()),
            url = it?.url ?: "",
            img = it?.img ?: it?.img_big ?: "",
            logo = it?.logo ?: "",
            favoriteSort = it?.favorite ?: false,
        )
}