package joe.barker.remote

import joe.barker.remote.movieDetails.MovieDetailsRemoteCalls
import joe.barker.remote.movieDetails.MovieDetailsRemoteImpl
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.Result
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.mock.Calls

class MovieDetailsRemoteShould {
    private val apiKey = BaseRemote.API_KEY

    @Test
    fun `Return movie details response on successful call`(){
        val id: Long = 1
        val responseBody = MovieDetailsResponse(id, "title", "tagline", "overview", "2020-01-02")
        val remoteCalls = mock<MovieDetailsRemoteCalls> {
            on { retrieveMovie(id, apiKey) }.doReturn(Calls.response(responseBody))
        }
        val repository = MovieDetailsRemoteImpl(remoteCalls)

        val expected = Result.Success(responseBody)
        assertEquals(expected, repository.getMovieDetails(id))
    }
}