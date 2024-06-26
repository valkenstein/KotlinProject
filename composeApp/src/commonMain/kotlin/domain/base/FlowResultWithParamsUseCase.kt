package domain.base

import domain.model.ResultDom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class FlowResultWithParamsUseCase<Param, Data> {
    operator fun invoke(params: Param): Flow<ResultDom<Data>> = flow {
        emit(retrieveData(params).apply {
            if (this.isSuccess())
                this.asSuccess().value = handlingOutcome(params, this.asSuccess().value)
        })
    }

    abstract suspend fun retrieveData(params: Param): ResultDom<Data>

    open suspend fun handlingOutcome(params: Param, value: Data): Data {
        return value
    }
}