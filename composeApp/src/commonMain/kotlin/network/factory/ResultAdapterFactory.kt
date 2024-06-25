//package network.factory
//
//import com.sumrussia.retrofit_result.ResultCall
//import de.jensklingenberg.ktorfit.converter.Converter
//import retrofit2.Call
//import retrofit2.CallAdapter
//import retrofit2.Retrofit
//import java.lang.reflect.ParameterizedType
//import java.lang.reflect.Type
//import network.factory.result.Result
//
//class ResultAdapterFactory : Converter.Factory {
//
//    override fun get(
//        returnType: Type,
//        annotations: Array<Annotation>,
//        retrofit: Retrofit
//    ): CallAdapter<*, *>? {
//        val rawReturnType: Class<*> = getRawType(returnType)
//        if (rawReturnType == Call::class.java) {
//            if (returnType is ParameterizedType) {
//                val callInnerType: Type = getParameterUpperBound(0, returnType)
//                if (getRawType(callInnerType) == Result::class.java) {
//                    // resultType is Call<Result<*>> | callInnerType is Result<*>
//                    if (callInnerType is ParameterizedType) {
//                        //returnType is
//                        val resultInnerType = getParameterUpperBound(0, callInnerType)
//
//                        return ResultCallAdapter<Any?>(resultInnerType)
//                    }
//                    return ResultCallAdapter<Nothing>(Nothing::class.java)
//                }
//            }
//        }
//
//        return null
//    }
//}
//
//private class ResultCallAdapter<R>(private val type: Type) :
//    CallAdapter<R, Call<Result<R>>> {
//
//    override fun responseType() = type
//
//    override fun adapt(call: Call<R>): Call<Result<R>> = ResultCall(call)
//}
