//package com.sumrussia.retrofit_result
//
//import okio.Timeout
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.io.IOException
//import network.factory.result.Result
//import java.net.UnknownHostException
//
//internal class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, Result<T>>(proxy) {
//
//    override fun enqueueImpl(callback: Callback<Result<T>>) {
//        proxy.enqueue(ResultCallback(this, callback))
//    }
//
//    override fun cloneImpl(): ResultCall<T> {
//        return ResultCall(proxy.clone())
//    }
//
//    private class ResultCallback<T>(
//        private val proxy: ResultCall<T>,
//        private val callback: Callback<Result<T>>
//    ) : Callback<T> {
//        override fun onResponse(call: Call<T>, response: Response<T>) {
//            val result: Result<T>
//            if (response.isSuccessful) {
//                result = Result.Success.HttpResponse(
//                    value = response.body() as T,
//                    statusCode = response.code(),
//                    statusMessage = response.message(),
//                    url = call.request().url().toString(),
//                    errorBody = response.body().toString()
//                )
//            } else {
//                val body = response.raw().body()
//                result = Result.Failure.HttpError(
//                    HttpErrorException(
//                        statusCode = response.code(),
//                        response = body,
//                        errorBody = response.errorBody()
//                    )
//                )
//            }
//            //val r = Response.success(result)
//            callback.onResponse(proxy,  Response.success(result))
//        }
//
//
//        override fun onFailure(call: Call<T>, error: Throwable) {
//            val result = when (error) {
//                is retrofit2.HttpException -> {
//                    val body = error.response()?.raw()?.body()
//                    Result.Failure.HttpError(
//                        HttpErrorException(
//                            statusCode = error.code(),
//                            response = body,
//                            cause = error,
//                            errorBody = error.response()?.errorBody()
//                        )
//                    )
//                }
//                is UnknownHostException->{
//                    println()
//                    Result.Failure.Error(error)//тут ошибка когда не может достучаться до бека
//                }
//                is IOException -> {
//                    Result.Failure.Error(error)
//                }
//                else -> {
//                    Result.Failure.Error(error)
//                }
//            }
//
//            callback.onResponse(proxy, Response.success(result))
//        }
//    }
//
//    override fun timeout(): Timeout {
//        return proxy.timeout()
//    }
//}
