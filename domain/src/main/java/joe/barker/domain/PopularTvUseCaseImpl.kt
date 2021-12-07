package joe.barker.domain

import joe.barker.domain.boundary.repository.PopularTvRepository
import joe.barker.domain.boundary.useCase.PopularTvUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

class PopularTvUseCaseImpl(
    private val repository: PopularTvRepository
) : PopularTvUseCase {

    override fun getPopularTvShows(): Either<List<MediaDetails>?, ErrorEntity?> =
        repository.getPopularTvShows()
}
