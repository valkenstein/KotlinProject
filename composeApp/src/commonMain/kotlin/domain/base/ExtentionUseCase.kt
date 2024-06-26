package domain.base

import domain.model.ResultDom
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun <T> ResultDom<T>.isSuccess(): Boolean {
    return this is ResultDom.Success
}

fun <T> ResultDom<T>.asSuccess(): ResultDom.Success<T> {
    return this as ResultDom.Success<T>
}

fun <T> ResultDom<T>.ifSuccess(): ResultDom.Success<T>? {
    return if (this.isSuccess()) this.asSuccess() else null
}

fun <T> ResultDom<T>.asFailure(): ResultDom.Failure<*> {
    return this as ResultDom.Failure<*>
}

@OptIn(ExperimentalContracts::class)
fun <T> ResultDom<T>.isFailure(): Boolean {
    contract {
        returns(true) implies (this@isFailure is ResultDom.Failure<*>)
    }
    return this is ResultDom.Failure<*>
}