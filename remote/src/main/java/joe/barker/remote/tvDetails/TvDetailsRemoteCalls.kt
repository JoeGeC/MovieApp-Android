package joe.barker.remote.tvDetails

import joe.barker.repository.response.TvDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvDetailsRemoteCalls {
    @GET("tv/{id}")
    fun retrieveTvDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ) : Call<TvDetailsResponse>
}
