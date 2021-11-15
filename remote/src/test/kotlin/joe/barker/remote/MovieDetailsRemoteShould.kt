package joe.barker.remote

import joe.barker.remote.movieDetails.MovieDetailsRemoteCalls
import joe.barker.remote.movieDetails.MovieDetailsRemoteImpl
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.Result
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.Response
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

    @Test
    fun `Return movie details response on failure call`(){
        val id: Long = 1
        val errorCode = 404
        val json = "{\n" +
                "    \"success\": false,\n" +
                "    \"status_code\": $errorCode,\n" +
                "    \"status_message\": \"The resource you requested could not be found.\"\n" +
                "}"
        val response = Response.error<MovieDetailsResponse>(errorCode, ResponseBody.create(MediaType.parse("application/json"), json))
        val remoteCalls = mock<MovieDetailsRemoteCalls> {
            on { retrieveMovie(id, apiKey) }.doReturn(Calls.response(response))
        }
        val repository = MovieDetailsRemoteImpl(remoteCalls)

        val errorResponse = ErrorResponse(errorCode, "The resource you requested could not be found.")
        val expected = Result.Failure(errorResponse)
        assertEquals(expected, repository.getMovieDetails(id))
    }
}