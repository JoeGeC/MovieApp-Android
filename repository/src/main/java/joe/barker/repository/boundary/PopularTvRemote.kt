package joe.barker.repository.boundary

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMediaResponse
import joe.barker.repository.response.Result

interface PopularTvRemote {
    fun getPopularTvShows() : Result<PopularMediaResponse?, ErrorResponse?>
}
