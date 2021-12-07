package joe.barker.domain.useCase

import joe.barker.domain.boundary.repository.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.domain.boundary.useCase.MovieDetailsUseCase

class MovieDetailsUseCaseImpl(
    private val repository: MovieDetailsRepository
) : MovieDetailsUseCase {

    override suspend fun getMovieDetailsOf(movieId: Long): Either<MediaDetails?, ErrorEntity?> =
        repository.getMovieDetailsOf(movieId)
}