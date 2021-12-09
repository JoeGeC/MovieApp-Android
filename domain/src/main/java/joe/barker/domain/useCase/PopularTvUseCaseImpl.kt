package joe.barker.domain.useCase

import joe.barker.domain.boundary.repository.PopularTvRepository
import joe.barker.domain.boundary.useCase.PopularTvUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

class PopularTvUseCaseImpl(
    private val repository: PopularTvRepository
) : PopularTvUseCase {

    override suspend fun getPopularTvShows(): Either<List<MediaDetails>?, ErrorEntity?> =
        repository.getPopularTvShows()
}
