package joe.barker.repository.boundary

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MediaDetailsResponse
import joe.barker.repository.response.Result

interface MovieDetailsRemote {
    fun getMovieDetails(movieId: Long): Result<MediaDetailsResponse?, ErrorResponse?>
}