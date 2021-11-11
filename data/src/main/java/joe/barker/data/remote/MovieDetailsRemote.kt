package joe.barker.data.remote

import joe.barker.data.response.MovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsRemote {
    @GET("movie/{id}")
    fun retrieveMovie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetailsResponse>
}