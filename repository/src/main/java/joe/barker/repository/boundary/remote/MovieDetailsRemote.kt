package joe.barker.repository.boundary.remote

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.Result

interface MovieDetailsRemote {
    fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?>
}