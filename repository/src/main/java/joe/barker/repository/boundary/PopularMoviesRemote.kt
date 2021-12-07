package joe.barker.repository.boundary

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMovieResponse
import joe.barker.repository.response.Result

interface PopularMoviesRemote {
    fun getPopularMovies(): Result<PopularMovieResponse?, ErrorResponse?>
}
