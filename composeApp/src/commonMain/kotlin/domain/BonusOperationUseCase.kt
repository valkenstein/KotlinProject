package domain

import domain.base.FlowResultWithParamsUseCase
import network.data.BaseResponse
import network.data.BonusInfoResponse
import network.repository.Repository

class BonusOperationUseCase(private val repository: Repository) :
    FlowResultWithParamsUseCase<String, BaseResponse<BonusInfoResponse>>() {
    override suspend fun retrieveData(params: String): BaseResponse<BonusInfoResponse> {
        return repository.getListData()
    }
}