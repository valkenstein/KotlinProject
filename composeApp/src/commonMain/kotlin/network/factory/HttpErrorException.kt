//package com.sumrussia.retrofit_result
//
//import network.factory.result.HttpException
//import okhttp3.ResponseBody
//
//class HttpErrorException(
//    statusCode: Int,
//    cause: Throwable? = null,
//    val response: ResponseBody? = null,
//    val errorBody: ResponseBody? = null
//) : HttpException(statusCode, cause = cause, errorBodyMessage = errorBody?.string().toString())