package joe.barker.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import joe.barker.local.tvDetails.TvDetails
import joe.barker.local.tvDetails.TvDetailsLocalImpl
import joe.barker.repository.response.TvDetailsResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TvDetailsLocalShould {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val database = Room.inMemoryDatabaseBuilder(
        context, TvDatabase::class.java).build()
    private val dao = database.tvDetailsDao()

    private val id: Long = 1
    private val name = "title"
    private val tagline = "tagline"
    private val overview = "overview"
    private val firstAirDate = "2020-01-02"
    private val posterId = "2020-01-02"
    private val score = 1.1f
    private val backdropPath = "backdrop.png"
    private val tvDetails = TvDetails(id, name, tagline, overview, firstAirDate, posterId, score, backdropPath)
    private val tvDetailsResponse = TvDetailsResponse(id, name, tagline, overview, firstAirDate, posterId, score, backdropPath)
    private val local = TvDetailsLocalImpl(database)

    @Test
    fun insertTvDetailsToDatabase(){
        local.insert(tvDetailsResponse)
        assertEquals(tvDetails, dao.findById(id))
    }

    @Test
    fun getTvShowById(){
        dao.insertAll(tvDetails)
        assertEquals(tvDetailsResponse, local.getTvShow(id))
    }

    @Test
    fun getNullIfTvShowDoesntExist(){
        assertEquals(null, local.getTvShow(id))
    }
}