package domain.usecase


import domain.base.FlowResultUseCase
import domain.mapper.StatusAccumulativeMapper
import domain.model.AccumulativeDom
import network.repository.ContentHomeRepository

class AccumulativeStatusUseCase constructor(
    private val catalogRepo: ContentHomeRepository,
    private val statusAccumulativeMapper: StatusAccumulativeMapper
) : FlowResultUseCase<List<AccumulativeDom>>() {
    override suspend fun retrieveData() =
        statusAccumulativeMapper.map { catalogRepo.getInfoAccumulativeStatus() }
}