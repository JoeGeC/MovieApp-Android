package joe.barker.domain.useCase

import joe.barker.domain.boundary.repository.TvDetailsRepository
import joe.barker.domain.boundary.useCase.TvDetailsUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails

class TvDetailsUseCaseImpl(
    private val repository: TvDetailsRepository
) : TvDetailsUseCase {

    override fun getTvDetailsOf(id: Long): Either<MediaDetails?, ErrorEntity?> =
        repository.getTvDetailsOf(id)
}
