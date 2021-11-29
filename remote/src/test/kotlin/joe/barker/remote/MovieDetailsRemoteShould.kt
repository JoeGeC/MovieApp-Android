package joe.barker.remote

import joe.barker.remote.movieDetails.MovieDetailsRemoteCalls
import joe.barker.remote.movieDetails.MovieDetailsRemoteImpl
import joe.barker.repository.response.Result
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.mock.Calls

class MovieDetailsRemoteShould {
    private val apiKey = BaseRemote.API_KEY
    private val movieId: Long = MovieDetailsRemoteTestProvider.movieId1

    @Test
    fun `Return movie details response on successful call`(){
        val response = MovieDetailsRemoteTestProvider.response1
        val remoteCalls = mock<MovieDetailsRemoteCalls> {
            on { retrieveMovie(movieId, apiKey) }.doReturn(Calls.response(response))
        }
        val repository = MovieDetailsRemoteImpl(remoteCalls)

        val expected = Result.Success(response)
        assertEquals(expected, repository.getMovieDetails(movieId))
    }
}