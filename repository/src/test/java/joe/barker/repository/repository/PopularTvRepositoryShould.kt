package joe.barker.repository.repository

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.repository.MediaDetailsRepoTestProvider
import joe.barker.repository.boundary.PopularTvRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.PopularTvResponse
import joe.barker.repository.response.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class PopularTvRepositoryShould {
    private val response = PopularTvResponse(listOf(MediaDetailsRepoTestProvider.tvDetailsResponse))
    private val mediaList = listOf(MediaDetailsRepoTestProvider.mediaDetails)

    @Test
    fun `Get popular TV shows from remote`(){
        val remote = mock<PopularTvRemote> {
            on { getPopularTvShows() } doReturn Result.Success(response)
        }
        val repository = PopularTvRepositoryImpl(remote)

        val result = runBlocking { repository.getPopularTvShows() }

        val expected = Either.Success(mediaList)
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `Return failure when error response from remote`(){
        val errorMessage = "error"
        val errorResponse = ErrorResponse(errorMessage)
        val remote = mock<PopularTvRemote> {
            on { getPopularTvShows() } doReturn Result.Failure(errorResponse)
        }
        val repository = PopularTvRepositoryImpl(remote)

        val result = runBlocking { repository.getPopularTvShows() }

        val error = ErrorEntity(errorMessage)
        val expected = Either.Failure(error)
        Assertions.assertEquals(expected, result)
    }
}