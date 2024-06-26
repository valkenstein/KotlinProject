package domain.usecase

import domain.base.FlowResultWithParamsUseCase
import domain.mapper.ProfileAccumulativeInfoMapper
import domain.model.BonusOperationsDom
import domain.model.ResultDom
import network.repository.Repository

class BonusOperationUseCase(
    private val repository: Repository,
    private val profileAccumulativeInfoMapper: ProfileAccumulativeInfoMapper
) :
    FlowResultWithParamsUseCase<String, BonusOperationsDom>() {
    override suspend fun retrieveData(params: String): ResultDom<BonusOperationsDom> =
        profileAccumulativeInfoMapper.map(repository.getListData())

}