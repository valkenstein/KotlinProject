package domain.mapper.base

import domain.model.ResultDom
import network.data.BaseResponse

abstract class ResultMapper<Source : Any, Target> : BaseResultMapper<Source, Target>() {
    protected abstract fun mapSuccessResult(src: Source?): Target
   override suspend fun map(src: suspend () -> BaseResponse<Source>): ResultDom<Target> {
        return try {
            fullResultExceptionToRuleMapper(src.invoke(), ::mapSuccessResult)
        } catch (e: Exception) {
            handleFailureResult(e, e.message.toString())
        }
    }
}