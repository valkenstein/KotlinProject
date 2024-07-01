package domain.mapper.base

import domain.model.ResultDom
import network.data.BaseResponse
import network.data.InviteResponse
import kotlin.reflect.KSuspendFunction2

abstract class FullResultMapper<Source, Target> : BaseResultMapper<Source, Target>() {
    protected abstract fun mapSuccessResult(src: BaseResponse<Source>): Target
    override suspend fun map(src: suspend () -> BaseResponse<Source>): ResultDom<Target> {
        return try {
            mapFullResult(src.invoke(), ::mapSuccessResult)
        } catch (e: Exception) {
            handleFailureResult(e, e.message.toString())
        }
    }
}