package domain.model

sealed class ResultDom<out T>{
    sealed class Success<T> : ResultDom<T>() {
        abstract var value: T

        class Value<T>(override var value: T, val message: String = "") : Success<T>()

        data class HttpResponse<T>(
            override var value: T,
            val statusCode: Int,
            val statusMessage: String? = null,
            val url: String? = null,
            val errorBody: String?
        ) : Success<T>()

        object Empty : Success<Nothing>() {
            override var value: Nothing = error("No value")
            override fun toString() = "Success"
        }
    }
    sealed class Failure<E : Throwable>(open val error: E? = null) : ResultDom<Nothing>() {

        override fun toString() = "Failure($error)"
        class ErrorMessages(
            override val error: Throwable?,
            val message: String = "",
            val errorId: String = "",
        ) : Failure<Throwable>(error)

        class Error(override val error: Throwable?) : Failure<Throwable>(error)

        class HttpError(
            override val error: Throwable?,
            val statusCode: Int,
            val statusMessage: String?,
            val url: String?,
            val errorBody: String?
        ) : Failure<Throwable>(error)
    }
}