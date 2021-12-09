package joe.barker.domain.useCase

import joe.barker.domain.boundary.repository.PopularMoviesRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.domain.boundary.useCase.PopularMoviesUseCase

class PopularMoviesUseCaseImpl(
    private val repository: PopularMoviesRepository
) : PopularMoviesUseCase {

    override suspend fun getPopularMovies(): Either<List<MediaDetails>?, ErrorEntity?> =
        repository.getPopularMovies()
}
