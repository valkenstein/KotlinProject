package domain.mapper

import domain.mapper.base.FullResultMapper
import network.data.BaseResponse

class PhoneModelMapper  constructor(
) : FullResultMapper<Map<Any, Any>, String>() {
    override fun mapSuccessResult(src: BaseResponse<Map<Any, Any>>): String =
        src.toString()

}