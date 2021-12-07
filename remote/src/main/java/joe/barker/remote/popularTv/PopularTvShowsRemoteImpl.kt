package joe.barker.remote.popularTv

import joe.barker.remote.BaseRemote
import joe.barker.repository.boundary.PopularTvRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularTvResponse
import joe.barker.repository.response.Result

class PopularTvShowsRemoteImpl(
    private val remote: PopularTvRemoteCalls = retrofit.create(
        PopularTvRemoteCalls::class.java
    )
) : BaseRemote(), PopularTvRemote {

    override fun getPopularTvShows(): Result<PopularTvResponse?, ErrorResponse?> =
        tryRemote { remote.retrievePopularTvShows(API_KEY) }
}
