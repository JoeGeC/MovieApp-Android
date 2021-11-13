package joe.barker.remote

import joe.barker.data.Result
import joe.barker.data.boundary.MovieDetailsRemote
import joe.barker.data.response.ErrorResponse
import joe.barker.data.response.MovieDetailsResponse

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