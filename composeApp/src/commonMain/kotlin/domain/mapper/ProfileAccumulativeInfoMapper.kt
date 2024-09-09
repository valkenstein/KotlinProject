package domain.mapper

import domain.mapper.base.ResultMapper
import domain.model.AccumulativeDom
import domain.model.BonusAccountItemDom
import domain.model.BonusOperationsDom
import network.data.AccumulativeResponse
import network.data.BonusInfoResponse
import network.data.BonusOperationsResponse
import network.data.OperationsResponse

class ProfileAccumulativeInfoMapper constructor(
) : ResultMapper<BonusInfoResponse, BonusOperationsDom>() {

    override fun mapSuccessResult(src: BonusInfoResponse?): BonusOperationsDom {
        return bonusOperationsDomMap(src?.bonusOperations)
    }

    fun bonusOperationsDomMap(it: BonusOperationsResponse?) =
        BonusOperationsDom(
            isBonus = it?.isBonus?.toBooleanStrictOrNull() ?: false,
            count = it?.count?.toIntOrNull() ?: 0,
            date = it?.date ?: "",
            operations = it?.operations?.map {
                operationMap(it)
            } ?: emptyList(),
        )

    fun operationMap(it: OperationsResponse): BonusAccountItemDom {
        val type = if (it.typeValue == "plus") "+" else "–"
        return BonusAccountItemDom(
            date = it.date ?: "",
            text = it.title ?: "",
            bonus = ("${it.value}"),
            type = if (it.typeValue == "plus") "+" else "–"
        )
    }
    fun listAccumulativeMap(listAccumulative: List<AccumulativeResponse>?): List<AccumulativeDom> {
        return listAccumulative?.map {
            AccumulativeDom(
                id = it.id ?: "",
                title = it.title ?: "",
                sumFrom = it.sumFrom ?: "",
                image = it.image ?: "",
            )
        } ?: emptyList()
    }
}