package joe.barker.remote.popularMovies

import joe.barker.remote.BaseRemote
import joe.barker.remote.JsonAdapter
import joe.barker.repository.boundary.PopularMoviesRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMoviesResponse
import joe.barker.repository.response.Result
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class PopularMoviesRemoteImpl(
    private val remote: PopularMoviesRemoteCalls = retrofit.create(
        PopularMoviesRemoteCalls::class.java
    )
) : BaseRemote(), PopularMoviesRemote{

    override fun getPopularMovies(): Result<PopularMoviesResponse?, ErrorResponse?> =
        tryRemote { remote.retrievePopularMovies(API_KEY) }

}
