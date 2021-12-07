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
            on { getMovie(MediaDetailsRepoTestProvider.id) } doReturn
                    MediaDetailsRepoTestProvider.mediaDetailsResponse
        }
        val remote = mock<MovieDetailsRemote>()
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(MediaDetailsRepoTestProvider.id) }

        val expected = Either.Success(MediaDetailsRepoTestProvider.mediaDetails)
        assertEquals(result, expected)
    }

    @Test
    fun `Get movie details from remote and insert to local if not already in local`(){
        val local = mock<MovieDetailsLocal> {
            on { getMovie(MediaDetailsRepoTestProvider.id) } doReturn null
        }
        val remote = mock<MovieDetailsRemote> {
            on { getMovieDetails(MediaDetailsRepoTestProvider.id) } doReturn
                    Result.Success(MediaDetailsRepoTestProvider.mediaDetailsResponse)
        }
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(MediaDetailsRepoTestProvider.id) }

        val expected = Either.Success(MediaDetailsRepoTestProvider.mediaDetails)
        assertEquals(expected, result)
        verify(local).insert(MediaDetailsRepoTestProvider.mediaDetailsResponse)
    }

    @Test
    fun `Return failure when error response from remote`(){
        val local = mock<MovieDetailsLocal> {
            on { getMovie(MediaDetailsRepoTestProvider.id) } doReturn null
        }
        val errorMessage = "error"
        val errorResponse = ErrorResponse(errorMessage)
        val remote = mock<MovieDetailsRemote> {
            on { getMovieDetails(MediaDetailsRepoTestProvider.id) } doReturn Result.Failure(errorResponse)
        }
        val repository = MovieDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getMovieDetailsOf(MediaDetailsRepoTestProvider.id) }

        val error = ErrorEntity(errorMessage)
        val expected = Either.Failure(error)
        assertEquals(expected, result)
    }
}