package network.factory.result

open class HttpException(
    val statusCode: Int,
    val statusMessage: String? = null,
    val url: String? = null,
    cause: Throwable? = null,
    val errorBodyMessage: String? = null
) : Exception(null, cause)
