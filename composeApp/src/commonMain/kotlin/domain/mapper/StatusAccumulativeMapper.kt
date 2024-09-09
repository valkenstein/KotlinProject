package domain.mapper


import domain.mapper.base.ResultMapper
import domain.model.AccumulativeDom
import network.data.StatusesResponse

class StatusAccumulativeMapper constructor(
    private val profileAccumulativeInfoMapper: ProfileAccumulativeInfoMapper
) : ResultMapper<StatusesResponse, List<AccumulativeDom>>() {

    override fun mapSuccessResult(src: StatusesResponse?): List<AccumulativeDom> {
        return profileAccumulativeInfoMapper.listAccumulativeMap(src?.statuses)
    }
}