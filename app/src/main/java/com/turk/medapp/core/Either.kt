package com.turk.medapp.core

//Please note that the either class itself doesn't hold any values. Its child classes does.
sealed class Either<out ErrorType, out ResultType> {
    // by FP conventions left would be the failure of this Either
    data class Error<out ErrorType>(val error: ErrorType) : Either<ErrorType, Nothing>()

    // by FP conventions right would be the Success of this Either
    data class Result<out ResultType>(val data: ResultType) : Either<Nothing, ResultType>()

    fun <ErrorType> left(a: ErrorType) = Error(a)
    fun <ResultType> right(b: ResultType) = Result(b)

    fun either(fnL: (ErrorType) -> Unit, fnR: (ResultType) -> Unit): Any =
        when (this) {
            is Error -> fnL(error)
            is Result -> fnR(data)
        }

    suspend fun eitherAsync(
        fnL: suspend (ErrorType) -> Any,
        fnR: suspend (ResultType) -> Unit
    ): Any =
        when (this) {
            is Error -> fnL(error)
            is Result -> fnR(data)
        }
}

infix fun <ErrorType, ResultType, MappedResultType> Either<ErrorType, ResultType>.map(f: (ResultType) -> MappedResultType): Either<ErrorType, MappedResultType> =
    when (this) {
        is Either.Error -> this
        is Either.Result -> Either.Result(f(this.data))
    }
