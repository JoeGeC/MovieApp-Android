package joe.barker.data.repository

import joe.barker.data.adapter.convert
import joe.barker.data.boundary.MovieDetailsLocal
import joe.barker.data.boundary.MovieDetailsRemote
import joe.barker.data.response.MovieDetailsResponse
import joe.barker.data.response.Result
import joe.barker.domain.boundary.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails

class MovieDetailsRepositoryImpl(private val local: MovieDetailsLocal, private val remote: MovieDetailsRemote) : MovieDetailsRepository {

    override suspend fun getMovieDetailsOf(movieId: Long): Either<MovieDetails?, ErrorEntity?> {
        val response = local.getMovie(movieId)
            ?: return getMovieDetailsFromRemote(movieId)
        return Either.Success(response.convert())
    }

    private fun getMovieDetailsFromRemote(movieId: Long): Either<MovieDetails?, ErrorEntity?> {
        val response = remote.getMovieDetails(movieId)
        return if (response.isSuccess) {
            val success = Result.Success(response.body)
            local.insert(success.value as MovieDetailsResponse)
            Either.Success(success.value.convert())
        } else Either.Failure((response as Result.Failure).convert())
    }
}