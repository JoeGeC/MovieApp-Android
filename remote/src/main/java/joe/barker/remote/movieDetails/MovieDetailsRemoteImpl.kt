package joe.barker.remote.movieDetails

import joe.barker.remote.BaseRemote
import joe.barker.repository.response.Result
import joe.barker.repository.boundary.remote.MovieDetailsRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MovieDetailsResponse

class MovieDetailsRemoteImpl(
    private val remote: MovieDetailsRemoteCalls = retrofit.create(
        MovieDetailsRemoteCalls::class.java
    )
) : BaseRemote(), MovieDetailsRemote {

    override suspend fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?> =
        tryRemote { remote.retrieveMovie(movieId, API_KEY) }
}