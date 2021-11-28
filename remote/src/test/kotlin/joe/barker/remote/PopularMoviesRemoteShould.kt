package joe.barker.remote

import joe.barker.remote.movieDetails.MovieDetailsRemoteCalls
import joe.barker.remote.movieDetails.MovieDetailsRemoteImpl
import joe.barker.remote.popularMovies.PopularMoviesRemoteCalls
import joe.barker.remote.popularMovies.PopularMoviesRemoteImpl
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.PopularMoviesResponse
import joe.barker.repository.response.Result
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.Response
import retrofit2.mock.Calls
import java.lang.Exception

class PopularMoviesRemoteShould {
    private val apiKey = BaseRemote.API_KEY
    private val movieId: Long = MovieDetailsRemoteTestProvider.movieId1
    private val response = PopularMoviesResponse(MovieDetailsRemoteTestProvider.responseList)
    private val errorCode = 404
    private val errorsJson = "{\n" +
            "    \"success\": false,\n" +
            "    \"status_code\": $errorCode,\n" +
            "    \"status_message\": \"The resource you requested could not be found.\"\n" +
            "}"

    @Test
    fun `Return movie details response on successful call`(){
        val remoteCalls = mock<PopularMoviesRemoteCalls> {
            on { retrievePopularMovies(apiKey) }.doReturn(Calls.response(response))
        }
        val repository = PopularMoviesRemoteImpl(remoteCalls)

        val expected = Result.Success(response)
        Assertions.assertEquals(expected, repository.getPopularMovies())
    }

    @Test
    fun `Return error response on failure call`(){
        val response = Response.error<MovieDetailsResponse>(errorCode, ResponseBody.create(MediaType.parse("application/json"), errorsJson))
        val remoteCalls = mock<MovieDetailsRemoteCalls> {
            on { retrieveMovie(movieId, apiKey) }.doReturn(Calls.response(response))
        }
        val repository = MovieDetailsRemoteImpl(remoteCalls)

        val errorResponse = ErrorResponse("The resource you requested could not be found.")
        val expected = Result.Failure(errorResponse)
        Assertions.assertEquals(expected, repository.getMovieDetails(movieId))
    }

    @Test
    fun `Return error response on exception`(){
        val errorMessage = "error"
        val remoteCalls = mock<MovieDetailsRemoteCalls> {
            on { retrieveMovie(movieId, apiKey) } doAnswer { throw Exception(errorMessage) }
        }
        val repository = MovieDetailsRemoteImpl(remoteCalls)

        val errorResponse = ErrorResponse(errorMessage)
        val expected = Result.Failure(errorResponse)
        Assertions.assertEquals(expected, repository.getMovieDetails(movieId))
    }
}