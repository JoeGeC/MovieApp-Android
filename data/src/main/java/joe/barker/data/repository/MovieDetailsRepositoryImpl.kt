package joe.barker.data.repository

import joe.barker.data.boundary.MovieDetailsLocal
import joe.barker.data.boundary.MovieDetailsRemote
import joe.barker.data.response.MovieDetailsResponse
import joe.barker.domain.boundary.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import joe.barker.data.Result

class MovieDetailsRepositoryImpl(
    private val local: MovieDetailsLocal,
    private val remote: MovieDetailsRemote
) : MovieDetailsRepository {

    override suspend fun getMovieDetailsOf(movieId: Long): Either<MovieDetails?, ErrorEntity?> {
        val response = local.getMovie(movieId)
            ?: return getMovieDetailsFromRemote(movieId)
        return Either.Success(response.convert())
    }

    private fun getMovieDetailsFromRemote(movieId: Long): Either<MovieDetails?, ErrorEntity?> {
        val response = remote.getMovieDetails(movieId)
        return if(response.isSuccess){
            val success = Result.Success(response.body)
            local.insertAll(success.value as MovieDetailsResponse)
            Either.Success(success.convert())
        } else {
            val failure = Result.Failure(response)
            Either.Failure(failure.convert())
        }
    }
}

private fun Result.Success<MovieDetailsResponse?>.convert() = MovieDetails(
    (this.value as MovieDetailsResponse).id!!,
    this.value.title!!,
    LocalDate.parse(this.value.release_date, DateTimeFormatter.ISO_DATE),
    this.value.tagline!!,
    this.value.overview!!
)

private fun MovieDetailsResponse.convert() = MovieDetails(
    this.id!!,
    this.title!!,
    LocalDate.parse(this.release_date, DateTimeFormatter.ISO_DATE),
    this.tagline!!,
    this.overview!!
)