package joe.barker.repository.boundary.remote

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularTvResponse
import joe.barker.repository.response.Result

interface PopularTvRemote {
    suspend fun getPopularTvShows() : Result<PopularTvResponse?, ErrorResponse?>
}
