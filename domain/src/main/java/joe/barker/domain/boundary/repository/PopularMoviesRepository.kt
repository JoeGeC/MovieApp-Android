package joe.barker.domain.boundary.repository

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails

interface PopularMoviesRepository {
    fun getPopularMovies() : Either<List<MovieDetails>?, ErrorEntity?>
}
