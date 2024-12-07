package com.turk.medapp.core.extension

import com.turk.medapp.core.Either
import com.turk.medapp.core.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


fun <T> Flow<T>.asEither(): Flow<Either<Failure, T>> {
    return this
        .map<T, Either<Failure, T>> {
            Either.Result(it)
        }
        .catch() {
            it.printStackTrace()
            emit(Either.Error(Failure.Unknown(it)))
        }
}


fun <T, R> Flow<T>.asEither(transform: (T) -> R): Flow<Either<Failure, R>> {
    return this
        .map<T, Either<Failure, R>> {
            Either.Result(transform(it))
        }
        .catch {
            emit(Either.Error(Failure.Unknown(it)))
        }
}