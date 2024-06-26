package domain.mapper.base

import domain.model.ResultDom
import network.data.BaseResponse


abstract class BaseResultMapper<Source, Target> :
    Mapper<BaseResponse<Source>, ResultDom<Target>> {
    private fun handleSuccessResult(
        value: BaseResponse<Source>,
        mapFunction: (BaseResponse<Source>) -> Target
    ): ResultDom<Target> {
        return if (value.errorId?.firstOrNull() == '4') {
            handleFailureResult(Throwable(value.message.toString()), errorId = value.errorId ?: "")
        } else {
            ResultDom.Success.Value(mapFunction(value))
        }
    }

    private fun handleFailureResult(error: Throwable?, errorId: String): ResultDom<Target> {
        return ResultDom.Failure.ErrorMessages(error, errorId = errorId)
    }

    private fun mapResult(
        src: BaseResponse<Source>,
        mapSuccessResult: (BaseResponse<Source>) -> Target,
    ): ResultDom<Target> {
       return try {
            handleSuccessResult(src) { mapSuccessResult(src) }
//            val value = src
//            ResultDom.Success.Value(handleSuccessResult(params, retrieveData(params)))
        } catch (e: Exception) {
            handleFailureResult(
                e,
                e.message.toString(),
              //  "0"//src.asFailure().error ?: "" // тут краш будет
            )
            ResultDom.Failure.Error(Throwable(e.message))
        }
//        return if (src.isSuccess()) {
//            val value = src.asSuccess().value
//            handleSuccessResult(value) { mapSuccessResult(value) }
//        } else {
//            handleFailureResult(
//                src.asFailure().error,
//                "0"//src.asFailure().error ?: "" // тут краш будет
//            )
//        }
    }

    protected fun mapFullResult(
        src: BaseResponse<Source>,
        mapSuccessResult: (BaseResponse<Source>) -> Target
    ): ResultDom<Target> {
        return mapResult(src, mapSuccessResult)
    }

    protected fun fullResultExceptionToRuleMapper(
        src: BaseResponse<Source>,
        mapSuccessResult: (Source?) -> Target
    ): ResultDom<Target> {
        return mapResult(src) {
            mapSuccessResult(it.data)
        }
    }
}

