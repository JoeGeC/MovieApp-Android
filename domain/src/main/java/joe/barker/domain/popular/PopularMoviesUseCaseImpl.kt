package joe.barker.domain.popular

import joe.barker.domain.boundary.PopularMoviesRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails

class PopularMoviesUseCaseImpl(private val repository: PopularMoviesRepository): PopularMoviesUseCase {
    override fun getPopularMovies(): Either<List<MovieDetails>?, ErrorEntity?> =
        repository.getPopularMovies()
}
