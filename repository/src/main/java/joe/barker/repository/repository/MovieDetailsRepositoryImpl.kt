package joe.barker.repository.repository

import joe.barker.repository.adapter.convert
import joe.barker.repository.boundary.local.MovieDetailsLocal
import joe.barker.repository.boundary.remote.MovieDetailsRemote
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.Result
import joe.barker.domain.boundary.repository.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

class MovieDetailsRepositoryImpl(private val local: MovieDetailsLocal, private val remote: MovieDetailsRemote) :
    MovieDetailsRepository {

    override suspend fun getMovieDetailsOf(movieId: Long): Either<MediaDetails?, ErrorEntity?> {
        val response = local.getMovie(movieId)
            ?: return getMovieDetailsFromRemote(movieId)
        return Either.Success(response.convert())
    }

    private suspend fun getMovieDetailsFromRemote(movieId: Long): Either<MediaDetails?, ErrorEntity?> {
        val response = remote.getMovieDetails(movieId)
        return if (response.isSuccess) {
            val success = Result.Success(response.body)
            local.insert(success.value as MovieDetailsResponse)
            Either.Success(success.value.convert())
        } else Either.Failure((response as Result.Failure).convert())
    }
}