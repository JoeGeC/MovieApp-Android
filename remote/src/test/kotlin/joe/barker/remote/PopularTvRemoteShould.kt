package joe.barker.remote

import joe.barker.remote.popularMovies.PopularMoviesRemoteImpl
import joe.barker.remote.popularTv.PopularTvRemoteCalls
import joe.barker.remote.popularTv.PopularTvShowsRemoteImpl
import joe.barker.repository.response.PopularMediaResponse
import joe.barker.repository.response.Result
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.mock.Calls

class PopularTvRemoteShould {
    private val apiKey = BaseRemote.API_KEY
    private val response = PopularMediaResponse(MovieDetailsRemoteTestProvider.responseList)

    @Test
    fun `Return tv show details response on successful call`(){
        val remoteCalls = mock<PopularTvRemoteCalls> {
            on { retrievePopularTvShows(apiKey) }.doReturn(Calls.response(response))
        }
        val remote = PopularTvShowsRemoteImpl(remoteCalls)

        val expected = Result.Success(response)
        Assertions.assertEquals(expected, remote.getPopularTvShows())
    }
}