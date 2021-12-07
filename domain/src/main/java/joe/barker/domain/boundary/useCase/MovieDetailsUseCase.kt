package joe.barker.domain.boundary.useCase

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

interface MovieDetailsUseCase {
    suspend fun getMovieDetailsOf(movieId: Long): Either<MediaDetails?, ErrorEntity?>
}