package joe.barker.domain.popular

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails

interface PopularMoviesUseCase {
    fun getPopularMovies() : Either<List<MovieDetails>?, ErrorEntity?>
}