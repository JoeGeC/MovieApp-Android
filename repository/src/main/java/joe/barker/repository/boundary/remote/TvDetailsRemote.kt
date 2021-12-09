package joe.barker.repository.boundary.remote

import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.Result
import joe.barker.repository.response.TvDetailsResponse

interface TvDetailsRemote {
    suspend fun getTvDetails(id: Long): Result<TvDetailsResponse?, ErrorResponse?>
}
