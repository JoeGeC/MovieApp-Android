package joe.barker.data.remote

import joe.barker.data.response.MovieDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsRemote {
    @GET("movie/{id}")
    fun retrieveMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetailResponse>
}