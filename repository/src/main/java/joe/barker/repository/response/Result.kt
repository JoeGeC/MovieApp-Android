package joe.barker.repository.response

import joe.barker.domain.entity.ErrorEntity

sealed class Result<out S, out F> {
    data class Success<out S>(val value: S) : Result<S, Nothing>()
    data class Failure<out F>(val error: F) : Result<Nothing, F>() {
        fun convert(): ErrorEntity {
            return ErrorEntity((this.error as ErrorResponse).status_message)
        }
    }

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<F>
    val body get() = (this as Success).value
    val errorBody get() = (this as Failure).error
}