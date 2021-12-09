package joe.barker.repository.repository

import joe.barker.domain.boundary.repository.PopularTvRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.adapter.convertTvResponse
import joe.barker.repository.boundary.remote.PopularTvRemote

class PopularTvRepositoryImpl(
    private val remote: PopularTvRemote
) : PopularTvRepository {

    override suspend fun getPopularTvShows(): Either<List<MediaDetails>?, ErrorEntity?> =
        remote.getPopularTvShows().convertTvResponse()
}
