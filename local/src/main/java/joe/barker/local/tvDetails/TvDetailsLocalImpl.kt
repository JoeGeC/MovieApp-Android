package joe.barker.local.tvDetails

import joe.barker.local.TvDatabase
import joe.barker.repository.boundary.local.TvDetailsLocal
import joe.barker.repository.response.TvDetailsResponse

class TvDetailsLocalImpl(private val database: TvDatabase) : TvDetailsLocal {
    override fun insert(tvDetailsResponse: TvDetailsResponse) =
        database.tvDetailsDao().insertAll(tvDetailsResponse.convert())

    override fun getTvShow(id: Long) : TvDetailsResponse? =
        database.tvDetailsDao().findById(id)?.convert()
}
