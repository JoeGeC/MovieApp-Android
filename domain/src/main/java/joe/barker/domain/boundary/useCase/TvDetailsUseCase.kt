package joe.barker.domain.boundary.useCase

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

interface TvDetailsUseCase {
    suspend fun getTvDetailsOf(id: Long) : Either<MediaDetails?, ErrorEntity?>
}
