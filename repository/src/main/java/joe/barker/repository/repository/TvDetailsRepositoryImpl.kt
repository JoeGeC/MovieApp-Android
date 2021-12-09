package joe.barker.repository.repository

import joe.barker.domain.boundary.repository.TvDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.adapter.convert
import joe.barker.repository.boundary.local.TvDetailsLocal
import joe.barker.repository.boundary.remote.TvDetailsRemote
import joe.barker.repository.response.Result
import joe.barker.repository.response.TvDetailsResponse

class TvDetailsRepositoryImpl(
    private val local: TvDetailsLocal,
    private val remote: TvDetailsRemote
) : TvDetailsRepository {

    override suspend fun getTvDetailsOf(id: Long): Either<MediaDetails?, ErrorEntity?> {
        val response = local.getTvShow(id)
            ?: return getTvDetailsFromRemote(id)
        return Either.Success(response.convert())
    }

    private suspend fun getTvDetailsFromRemote(movieId: Long): Either<MediaDetails?, ErrorEntity?> {
        val response = remote.getTvDetails(movieId)
        return if (response.isSuccess) {
            val success = Result.Success(response.body)
            local.insert(success.value as TvDetailsResponse)
            Either.Success(success.value.convert())
        } else Either.Failure((response as Result.Failure).convert())
    }
}
