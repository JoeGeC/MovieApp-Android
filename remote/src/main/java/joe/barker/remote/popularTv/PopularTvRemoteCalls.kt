package joe.barker.remote.popularTv

import joe.barker.repository.response.PopularMediaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularTvRemoteCalls {
    @GET("tv/popular")
    fun retrievePopularTvShows(
        @Query("api_key") apiKey: String
    ) : Call<PopularMediaResponse>
}
