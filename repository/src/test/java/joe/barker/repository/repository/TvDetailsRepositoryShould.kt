package joe.barker.repository.repository

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.repository.MediaDetailsRepoTestProvider
import joe.barker.repository.boundary.local.TvDetailsLocal
import joe.barker.repository.boundary.remote.TvDetailsRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class TvDetailsRepositoryShould {
    @Test
    fun `Get tv details from local storage if existing`(){
        val local = mock<TvDetailsLocal> {
            on { getTvShow(MediaDetailsRepoTestProvider.id) } doReturn
                    MediaDetailsRepoTestProvider.tvDetailsResponse
        }
        val remote = mock<TvDetailsRemote>()
        val repository = TvDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getTvDetailsOf(MediaDetailsRepoTestProvider.id) }

        val expected = Either.Success(MediaDetailsRepoTestProvider.tvDetails)
        Assertions.assertEquals(result, expected)
    }

    @Test
    fun `Get tv details from remote and insert to local if not already in local`(){
        val local = mock<TvDetailsLocal> {
            on { getTvShow(MediaDetailsRepoTestProvider.id) } doReturn null
        }
        val remote = mock<TvDetailsRemote> {
            onBlocking { getTvDetails(MediaDetailsRepoTestProvider.id) } doReturn
                    Result.Success(MediaDetailsRepoTestProvider.tvDetailsResponse)
        }
        val repository = TvDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getTvDetailsOf(MediaDetailsRepoTestProvider.id) }

        val expected = Either.Success(MediaDetailsRepoTestProvider.tvDetails)
        Assertions.assertEquals(expected, result)
        verify(local).insert(MediaDetailsRepoTestProvider.tvDetailsResponse)
    }

    @Test
    fun `Return failure when error response from remote`(){
        val local = mock<TvDetailsLocal> {
            on { getTvShow(MediaDetailsRepoTestProvider.id) } doReturn null
        }
        val errorMessage = "error"
        val errorResponse = ErrorResponse(errorMessage)
        val remote = mock<TvDetailsRemote> {
            onBlocking { getTvDetails(MediaDetailsRepoTestProvider.id) } doReturn Result.Failure(errorResponse)
        }
        val repository = TvDetailsRepositoryImpl(local, remote)

        val result = runBlocking { repository.getTvDetailsOf(MediaDetailsRepoTestProvider.id) }

        val error = ErrorEntity(errorMessage)
        val expected = Either.Failure(error)
        Assertions.assertEquals(expected, result)
    }
}