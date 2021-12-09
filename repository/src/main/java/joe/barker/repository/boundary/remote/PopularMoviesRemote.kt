package joe.barker.repository.boundary.remote

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMovieResponse
import joe.barker.repository.response.Result

interface PopularMoviesRemote {
    suspend fun getPopularMovies(): Result<PopularMovieResponse?, ErrorResponse?>
}
