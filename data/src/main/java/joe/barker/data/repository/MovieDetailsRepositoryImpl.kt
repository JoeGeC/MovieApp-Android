package joe.barker.data.repository

import joe.barker.data.JsonAdapter
import joe.barker.data.Result
import joe.barker.data.remote.MovieDetailsRemote
import joe.barker.data.response.MovieDetailResponse
import joe.barker.domain.boundary.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailsRepositoryImpl : Repository(), MovieDetailsRepository {
    private val remote = retrofit.create(MovieDetailsRemote::class.java)

    override suspend fun getMovieDetailsOf(movieId: Int): Either<MovieDetails?, ErrorEntity?> {
        val result = remote.retrieveMovie(movieId, API_KEY).execute()
        return if(result.isSuccessful) {
            val success = Result.Success(result.body())
            Either.Success(success.convert())
        } else{
            val errorResponse = JsonAdapter.convertToError(result)
            val failure = Result.Failure(errorResponse)
            return Either.Failure(failure.convert())
        }
    }
}

private fun Result.Success<MovieDetailResponse?>.convert(): MovieDetails = MovieDetails(
    (this.value as MovieDetailResponse).id!!,
    this.value.title!!,
    LocalDate.parse(this.value.release_date, DateTimeFormatter.ISO_DATE),
    this.value.tagline!!,
    this.value.overview!!
)
