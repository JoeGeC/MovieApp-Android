package joe.barker.domain.movieDetails

import joe.barker.domain.boundary.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails

class MovieDetailsUseCaseImpl(private val repository: MovieDetailsRepository) : MovieDetailsUseCase {
    override suspend fun getMovieDetailsOf(movieId: Long): Either<MovieDetails?, ErrorEntity?> =
        repository.getMovieDetailsOf(movieId)
}