package joe.barker.domain.movieDetails

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails

interface MovieDetailsUseCase {
    suspend fun getMovieDetailsOf(movieId: Long): Either<MovieDetails?, ErrorEntity?>
}