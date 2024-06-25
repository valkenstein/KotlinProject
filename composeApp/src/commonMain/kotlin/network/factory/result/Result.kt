@file:Suppress("unused")
package network.factory.result


sealed class Result<out T> {
    class Messages<STATE>(val state: STATE)

    sealed class Success<T> : Result<T>() {
        var messages: Messages<*>? = null
        var debug: String? = null
        abstract val value: T
        override fun toString() = "Success($value)"
        class Value<T>(override val value: T) : Success<T>()

        data class HttpResponse<T>(
            override val value: T,
            override val statusCode: Int,
            override val statusMessage: String? = null,
            override val url: String? = null,
            override val errorBody: String?
        ) : Success<T>(), network.factory.result.HttpResponse

        object Empty : Success<Nothing>() {
            override val value: Nothing get() = error("No value")
            override fun toString() = "Success"
        }
    }

    sealed class Failure<E : Throwable>(open val error: E? = null) : Result<Nothing>() {

        override fun toString() = "Failure($error)"
        class ErrorMessages(override val error: Throwable?) : Failure<Throwable>(error)
        class Error(override val error: Throwable?) : Failure<Throwable>(error)

        class HttpError(override val error: HttpException) : Failure<HttpException>(),
            HttpResponse {
            override val statusCode: Int get() = error.statusCode
            override val statusMessage: String? get() = error.statusMessage
            override val url: String? get() = error.url
            override val errorBody: String? get() = error.errorBodyMessage
        }
    }
}

typealias EmptyResult = Result<Nothing>
