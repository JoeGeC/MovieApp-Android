package joe.barker.repository.repository

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.repository.MediaDetailsRepoTestProvider
import joe.barker.repository.boundary.remote.PopularMoviesRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularMovieResponse
import joe.barker.repository.response.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class PopularMoviesRepositoryShould {
    private val response = PopularMovieResponse(listOf(MediaDetailsRepoTestProvider.movieDetailsResponse))
    private val movieList = listOf(MediaDetailsRepoTestProvider.movieDetails)

    @Test
    fun `Get popular movies from remote`(){
        val remote = mock<PopularMoviesRemote> {
            on { getPopularMovies() } doReturn Result.Success(response)
        }
        val repository = PopularMoviesRepositoryImpl(remote)

        val result = runBlocking { repository.getPopularMovies() }

        val expected = Either.Success(movieList)
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `Return failure when error response from remote`(){
        val errorMessage = "error"
        val errorResponse = ErrorResponse(errorMessage)
        val remote = mock<PopularMoviesRemote> {
            on { getPopularMovies() } doReturn Result.Failure(errorResponse)
        }
        val repository = PopularMoviesRepositoryImpl(remote)

        val result = runBlocking { repository.getPopularMovies() }

        val error = ErrorEntity(errorMessage)
        val expected = Either.Failure(error)
        Assertions.assertEquals(expected, result)
    }
}