package joe.barker.repository.repository

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.adapter.convert
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMediaResponse
import joe.barker.repository.response.Result

internal fun Result<PopularMediaResponse?, ErrorResponse?>.convert(): Either<List<MediaDetails>?, ErrorEntity?> {
    return if (isSuccess) {
        val success = Result.Success(body)
        Either.Success(success.value.convert())
    } else Either.Failure((this as Result.Failure).convert())
}