package joe.barker.remote.popularMovies

import joe.barker.remote.BaseRemote
import joe.barker.repository.boundary.PopularMoviesRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMediaResponse
import joe.barker.repository.response.Result

class PopularMoviesRemoteImpl(
    private val remote: PopularMoviesRemoteCalls = retrofit.create(
        PopularMoviesRemoteCalls::class.java
    )
) : BaseRemote(), PopularMoviesRemote{

    override fun getPopularMovies(): Result<PopularMediaResponse?, ErrorResponse?> =
        tryRemote { remote.retrievePopularMovies(API_KEY) }
}
