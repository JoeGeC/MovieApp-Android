package joe.barker.repository

import joe.barker.repository.boundary.MovieDetailsLocal
import joe.barker.repository.boundary.MovieDetailsRemote
import joe.barker.repository.repository.MovieDetailsRepositoryImpl
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.Result
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.time.LocalDate

class MovieDetailsRepositoryShould {
    private val movieId: Long = 1
    private val movieTitle = "Title"
    private val movieTagline = "Tagline"
    private val movieOverview = "Overview"
    private val movieReleaseDate = LocalDate.of(2020, 1, 2)
    private val movieReleaseDateString = "2020-01-02"
    private val movieDetailsResponse = MovieDetailsResponse(movieId, movieTitle, movieTagline, movieOverview, movieReleaseDateString)
    private val movieDetails = MovieDetails(movieId, movieTitle, movieReleaseDate, movieTagline, movieOverview)

    @Test
    fun `Get movie details from local storage if existing`(){
        val local = mock<MovieDetailsLocal> {
            on { getMovie(movieId) } doReturn movieDetailsResponse
        }
        val remote = mock<MovieDetailsRemote>()
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(movieId) }

        val expected = Either.Success(movieDetails)
        assertEquals(result, expected)
    }

    @Test
    fun `Get movie details from remote and insert to local if not already in local`(){
        val local = mock<MovieDetailsLocal> {
            on { getMovie(movieId) } doReturn null
        }
        val remote = mock<MovieDetailsRemote> {
            on { getMovieDetails(movieId) } doReturn Result.Success(movieDetailsResponse)
        }
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(movieId) }

        val expected = Either.Success(movieDetails)
        assertEquals(result, expected)
        verify(local).insert(movieDetailsResponse)
    }

    @Test
    fun `Return failure when error response from remote`(){
        val local = mock<MovieDetailsLocal> {
            on { getMovie(movieId) } doReturn null
        }
        val errorMessage = "error"
        val errorResponse = ErrorResponse(1, errorMessage)
        val remote = mock<MovieDetailsRemote> {
            on { getMovieDetails(movieId) } doReturn Result.Failure(errorResponse)
        }
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(movieId) }

        val error = ErrorEntity(errorMessage)
        val expected = Either.Failure(error)
        assertEquals(result, expected)
    }
}