package joe.barker.remote

import joe.barker.repository.response.MovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsRemoteCalls {
    @GET("movie/{id}")
    fun retrieveMovie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetailsResponse>
}