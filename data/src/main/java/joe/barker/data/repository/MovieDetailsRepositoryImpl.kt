package joe.barker.data.repository

import joe.barker.data.JsonAdapter
import joe.barker.data.Result
import joe.barker.data.boundary.MovieDetailsLocal
import joe.barker.data.remote.MovieDetailsRemote
import joe.barker.data.response.MovieDetailsResponse
import joe.barker.domain.boundary.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailsRepositoryImpl(private val movieDetailsLocal: MovieDetailsLocal) : Repository(), MovieDetailsRepository {
    private val remote = retrofit.create(MovieDetailsRemote::class.java)

    override suspend fun getMovieDetailsOf(movieId: Long): Either<MovieDetails?, ErrorEntity?> {
        val movieDetails = movieDetailsLocal.getMovie(movieId)
        if(movieDetails == null)
            return getMovieDetailsFromRemote(movieId)
        return Either.Success(movieDetails.convert())
    }

    private fun getMovieDetailsFromRemote(movieId: Long): Either<MovieDetails?, ErrorEntity?> {
        val result = remote.retrieveMovie(movieId, API_KEY).execute()
        return if (result.isSuccessful) {
            val success = Result.Success(result.body())
            movieDetailsLocal.insertAll(success.value as MovieDetailsResponse)
            Either.Success(success.convert())
        } else {
            val errorResponse = JsonAdapter.convertToError(result)
            val failure = Result.Failure(errorResponse)
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