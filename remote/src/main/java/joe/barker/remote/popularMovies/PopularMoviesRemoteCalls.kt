package joe.barker.remote.popularMovies

import joe.barker.repository.response.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesRemoteCalls {
    @GET("movie/popular")
    fun retrievePopularMovies(
        @Query("api_key") apiKey: String
    ) : Call<PopularMovieResponse>
}