package joe.barker.remote.movieDetails

import joe.barker.remote.BaseRemote
import joe.barker.repository.response.Result
import joe.barker.repository.boundary.MovieDetailsRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MediaDetailsResponse

class MovieDetailsRemoteImpl(
    private val remote: MovieDetailsRemoteCalls = retrofit.create(
        MovieDetailsRemoteCalls::class.java
    )
) : BaseRemote(), MovieDetailsRemote {

    override fun getMovieDetails(movieId: Long): Result<MediaDetailsResponse?, ErrorResponse?> =
        tryRemote { remote.retrieveMovie(movieId, API_KEY) }
}