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
abstract class FlowResultUseCase<T> {
    operator fun invoke(): Flow<ResultDom<T>> = flow {
        val result = retrieveData()
        if (result.isSuccess()) {
            emit(result)
        } else {
            emit(result)
        }
    }

    abstract suspend fun retrieveData(): ResultDom<T>
}
abstract class FlowResultWithDoubleParamsUseCase<Param1, Param2, Data> {
    operator fun invoke(params1: Param1, params2: Param2): Flow<ResultDom<Data>> = flow {
        emit(retrieveData(params1, params2))
    }

    abstract suspend fun retrieveData(params1: Param1, params2: Param2): ResultDom<Data>
}