package joe.barker.domain

import joe.barker.domain.boundary.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails

class MovieDetailsUseCaseImpl(private val repository: MovieDetailsRepository) {
    suspend fun getMovieDetailsOf(movieId: Int): Either<MovieDetails?, ErrorEntity?> =
        repository.getMovieDetailsOf(movieId)
}