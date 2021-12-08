package joe.barker.repository.boundary.local

import joe.barker.repository.response.TvDetailsResponse

interface TvDetailsLocal {
    fun getTvShow(id: Long) : TvDetailsResponse?
    fun insert(tvDetails: TvDetailsResponse)
}
