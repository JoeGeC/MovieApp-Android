package joe.barker.remote

import joe.barker.remote.tvDetails.TvDetailsRemoteCalls
import joe.barker.remote.tvDetails.TvDetailsRemoteImpl
import joe.barker.repository.response.Result
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.mock.Calls

class TvDetailsRemoteShould {
    private val apiKey = BaseRemote.API_KEY
    private val tvId: Long = MediaDetailsRemoteTestProvider.id1

    @Test
    fun `Return tv details response on successful call`(){
        val response = MediaDetailsRemoteTestProvider.tvResponse1
        val remoteCalls = mock<TvDetailsRemoteCalls> {
            on { retrieveTvDetails(tvId, apiKey) }.doReturn(Calls.response(response))
        }
        val repository = TvDetailsRemoteImpl(remoteCalls)

        val expected = Result.Success(response)
        Assertions.assertEquals(expected, repository.getTvDetails(tvId))
    }
}