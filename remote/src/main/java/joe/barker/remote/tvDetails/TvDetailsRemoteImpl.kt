package joe.barker.remote.tvDetails

import joe.barker.remote.BaseRemote
import joe.barker.repository.boundary.remote.TvDetailsRemote
import joe.barker.repository.response.ErrorResponse
import joe.barker.repository.response.Result
import joe.barker.repository.response.TvDetailsResponse

class TvDetailsRemoteImpl(
    private val remote: TvDetailsRemoteCalls = retrofit.create(
        TvDetailsRemoteCalls::class.java
    )
) : BaseRemote(), TvDetailsRemote {

    override fun getTvDetails(id: Long): Result<TvDetailsResponse?, ErrorResponse?> =
        tryRemote { remote.retrieveTvDetails(id, API_KEY) }
}
