package joe.barker.repository.boundary

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMoviesResponse
import joe.barker.repository.response.Result

interface PopularMoviesRemote {
    fun getPopularMovies(): Result<PopularMoviesResponse?, ErrorResponse?>
}
