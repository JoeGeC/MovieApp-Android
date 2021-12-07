package joe.barker.repository.repository

import joe.barker.domain.boundary.repository.PopularTvRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.adapter.convert
import joe.barker.repository.boundary.PopularTvRemote
import joe.barker.repository.response.Result

class PopularTvRepositoryImpl(
    private val remote: PopularTvRemote
) : PopularTvRepository {

    override fun getPopularTvShows(): Either<List<MediaDetails>?, ErrorEntity?> {
        val response = remote.getPopularTvShows()
        return if (response.isSuccess) {
            val success = Result.Success(response.body)
            Either.Success(success.value.convert())
        } else Either.Failure((response as Result.Failure).convert())
    }
}
