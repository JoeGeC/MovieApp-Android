package joe.barker.remote.movieDetails

import joe.barker.remote.BaseRemote
import joe.barker.remote.JsonAdapter
import joe.barker.repository.response.Result
import joe.barker.repository.boundary.MovieDetailsRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MovieDetailsResponse
import java.lang.Exception

class MovieDetailsRemoteImpl(
    private val remote: MovieDetailsRemoteCalls = retrofit.create(
        MovieDetailsRemoteCalls::class.java
    )
) : BaseRemote(), MovieDetailsRemote {

    override fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?> {
        return try{
            val result = remote.retrieveMovie(movieId, API_KEY).execute()
            return if (result.isSuccessful) {
                Result.Success(result.body())
            } else {
                val errorResponse = JsonAdapter.convertToError(result)
                Result.Failure(errorResponse)
            }
        } catch(exception: Exception){
            val error = ErrorResponse(-1, exception.localizedMessage)
            Result.Failure(error)
        }
//
    }
}