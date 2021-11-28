package joe.barker.remote.popularMovies

import joe.barker.remote.BaseRemote
import joe.barker.remote.JsonAdapter
import joe.barker.repository.boundary.PopularMoviesRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMoviesResponse
import joe.barker.repository.response.Result
import java.lang.Exception

class PopularMoviesRemoteImpl(private val remote: PopularMoviesRemoteCalls) : PopularMoviesRemote{
    override fun getPopularMovies(): Result<PopularMoviesResponse?, ErrorResponse?> {
        return try{
            val result = remote.retrievePopularMovies(BaseRemote.API_KEY).execute()
            return if (result.isSuccessful) {
                Result.Success(result.body())
            } else {
                val errorResponse = JsonAdapter.convertToError(result)
                Result.Failure(errorResponse)
            }
        } catch(exception: Exception){
            val error = ErrorResponse(exception.localizedMessage)
            Result.Failure(error)
        }
    }
}
