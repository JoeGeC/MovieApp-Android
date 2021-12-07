package joe.barker.repository.adapter

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.response.*

internal fun Result<PopularMovieResponse?, ErrorResponse?>.convertMovieResponse(): Either<List<MediaDetails>?, ErrorEntity?> {
    return if (isSuccess) {
        val success = Result.Success(body)
        Either.Success(success.value.convert())
    } else Either.Failure((this as Result.Failure).convert())
}

internal fun Result<PopularTvResponse?, ErrorResponse?>.convertTvResponse(): Either<List<MediaDetails>?, ErrorEntity?> {
    return if (isSuccess) {
        val success = Result.Success(body)
        Either.Success(success.value.convert())
    } else Either.Failure((this as Result.Failure).convert())
}

fun PopularMovieResponse?.convert(): List<MediaDetails>? =
    this?.results?.map { it.convert() }

fun PopularTvResponse?.convert(): List<MediaDetails>? =
    this?.results?.map { it.convert() }