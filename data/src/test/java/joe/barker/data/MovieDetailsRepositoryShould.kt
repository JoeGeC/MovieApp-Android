package joe.barker.data

import joe.barker.data.boundary.MovieDetailsLocal
import joe.barker.data.remote.MovieDetailsRemoteCalls
import joe.barker.data.repository.MovieDetailsRepositoryImpl
import joe.barker.data.response.MovieDetailsResponse
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.MovieDetails
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.Response
import java.time.LocalDate

class MovieDetailsRepositoryShould {
    private val movieId: Long = 1
    private val movieTitle = "Title"
    private val movieTagline = "Tagline"
    private val movieOverview = "Overview"
    private val movieReleaseDate = LocalDate.of(2020, 1, 2)
    private val movieReleaseDateString = "2020-01-02"

    @Test
    fun `Get movie details from local storage if existing`(){
        val movieDetailsResponse = MovieDetailsResponse(movieId, movieTitle, movieTagline, movieOverview, movieReleaseDateString)
        val local = mock<MovieDetailsLocal> {
            on { getMovie(movieId) } doReturn movieDetailsResponse
        }
        val repository = MovieDetailsRepositoryImpl(local)

        val movieDetails = MovieDetails(movieId, movieTitle, movieReleaseDate, movieTagline, movieOverview)

        val expected = Either.Success(movieDetails)
        val result = runBlocking { repository.getMovieDetailsOf(movieId) }
        assertEquals(result, expected)
    }

//    @Test
//    fun `Get movie details from remote if not in local`(){
//        val movieDetailsResponse = MovieDetailsResponse(movieId, movieTitle, movieTagline, movieOverview, movieReleaseDateString)
//        val local = mock<MovieDetailsLocal> {
//            on { getMovie(movieId) } doReturn null
//        }
//        val response = Response<MovieDetailsResponse>()
//        val remote = mock<MovieDetailsRemoteCalls>(){
//            on { retrieveMovie(movieId, anyString()).execute() } doReturn movieDetailsResponse
//        }
//        val repository = MovieDetailsRepositoryImpl(local, remote)
//
//        val movieDetails = MovieDetails(movieId, movieTitle, movieReleaseDate, movieTagline, movieOverview)
//
//        val expected = Either.Success(movieDetails)
//        val result = runBlocking { repository.getMovieDetailsOf(movieId) }
//        assertEquals(result, expected)
//    }
}