package joe.barker.remote.popularTv

import joe.barker.remote.BaseRemote
import joe.barker.repository.boundary.PopularTvRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMediaResponse
import joe.barker.repository.response.Result

class PopularTvShowsRemoteImpl(
    private val remote: PopularTvRemoteCalls
) : BaseRemote(), PopularTvRemote {

    override fun getPopularTvShows(): Result<PopularMediaResponse?, ErrorResponse?> =
        tryRemote { remote.retrievePopularTvShows(API_KEY) }
}
