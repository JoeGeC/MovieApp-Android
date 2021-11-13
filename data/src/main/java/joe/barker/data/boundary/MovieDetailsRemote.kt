package joe.barker.data.boundary

import joe.barker.data.response.ErrorResponse
import joe.barker.data.response.MovieDetailsResponse
import joe.barker.data.response.Result

interface MovieDetailsRemote {
    fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?>
}