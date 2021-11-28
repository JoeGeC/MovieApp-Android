package joe.barker.repository

import joe.barker.repository.boundary.MovieDetailsLocal
import joe.barker.repository.boundary.MovieDetailsRemote
import joe.barker.repository.repository.MovieDetailsRepositoryImpl
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.Result
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MovieDetailsRepositoryShould {
    @Test
    fun `Get movie details from local storage if existing`(){
        val local = mock<MovieDetailsLocal> {
            on { getMovie(MovieDetailsRepoTestProvider.movieId) } doReturn
                    MovieDetailsRepoTestProvider.movieDetailsResponse
        }
        val remote = mock<MovieDetailsRemote>()
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(MovieDetailsRepoTestProvider.movieId) }

        val expected = Either.Success(MovieDetailsRepoTestProvider.movieDetails)
        assertEquals(result, expected)
    }

    @Test
    fun `Get movie details from remote and insert to local if not already in local`(){
        val local = mock<MovieDetailsLocal> {
            on { getMovie(MovieDetailsRepoTestProvider.movieId) } doReturn null
        }
        val remote = mock<MovieDetailsRemote> {
            on { getMovieDetails(MovieDetailsRepoTestProvider.movieId) } doReturn
                    Result.Success(MovieDetailsRepoTestProvider.movieDetailsResponse)
        }
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(MovieDetailsRepoTestProvider.movieId) }

        val expected = Either.Success(MovieDetailsRepoTestProvider.movieDetails)
        assertEquals(expected, result)
        verify(local).insert(MovieDetailsRepoTestProvider.movieDetailsResponse)
    }

    @Test
    fun `Return failure when error response from remote`(){
        val local = mock<MovieDetailsLocal> {
            on { getMovie(MovieDetailsRepoTestProvider.movieId) } doReturn null
        }
        val errorMessage = "error"
        val errorResponse = ErrorResponse(errorMessage)
        val remote = mock<MovieDetailsRemote> {
            on { getMovieDetails(MovieDetailsRepoTestProvider.movieId) } doReturn Result.Failure(errorResponse)
        }
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(MovieDetailsRepoTestProvider.movieId) }

        val error = ErrorEntity(errorMessage)
        val expected = Either.Failure(error)
        assertEquals(expected, result)
    }
}