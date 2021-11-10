package joe.barker.domain.boundary

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetailsOf(movieId: Int) : Either<MovieDetails?, ErrorEntity?>
}
