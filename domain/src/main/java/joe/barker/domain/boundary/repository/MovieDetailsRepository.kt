package joe.barker.domain.boundary.repository

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetailsOf(movieId: Long) : Either<MediaDetails?, ErrorEntity?>
}
