package joe.barker.domain.boundary.repository

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

interface PopularMoviesRepository {
    suspend fun getPopularMovies() : Either<List<MediaDetails>?, ErrorEntity?>
}
