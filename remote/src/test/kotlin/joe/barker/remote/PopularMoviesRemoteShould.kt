package joe.barker.remote

import joe.barker.remote.popularMovies.PopularMoviesRemoteCalls
import joe.barker.remote.popularMovies.PopularMoviesRemoteImpl
import joe.barker.repository.response.PopularMovieResponse
import joe.barker.repository.response.Result
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.mock.Calls

class PopularMoviesRemoteShould {
    private val apiKey = BaseRemote.API_KEY
    private val response = PopularMovieResponse(MediaDetailsRemoteTestProvider.movieResponseList)

    @Test
    suspend fun `Return movie details response on successful call`(){
        val remoteCalls = mock<PopularMoviesRemoteCalls> {
            on { retrievePopularMovies(apiKey) }.doReturn(Calls.response(response))
        }
        val remote = PopularMoviesRemoteImpl(remoteCalls)

        val expected = Result.Success(response)
        Assertions.assertEquals(expected, remote.getPopularMovies())
    }
}