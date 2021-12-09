package joe.barker.domain.boundary.useCase

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

interface PopularTvUseCase {
    suspend fun getPopularTvShows() : Either<List<MediaDetails>?, ErrorEntity?>
}
