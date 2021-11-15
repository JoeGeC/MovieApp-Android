package joe.barker.remote.movieDetails

import joe.barker.remote.BaseRemote
import joe.barker.remote.JsonAdapter
import joe.barker.repository.response.Result
import joe.barker.repository.boundary.MovieDetailsRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MovieDetailsResponse

class MovieDetailsRemoteImpl : BaseRemote(), MovieDetailsRemote {
    private val remote: MovieDetailsRemoteCalls = retrofit.create(MovieDetailsRemoteCalls::class.java)

    override fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?> {
        val result = remote.retrieveMovie(movieId, API_KEY).execute()
        return if (result.isSuccessful) {
            Result.Success(result.body())
        } else {
            val errorResponse = JsonAdapter.convertToError(result)
            Result.Failure(errorResponse)
        }
    }
}