package joe.barker.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import joe.barker.data.response.MovieDetailsResponse
import joe.barker.local.moviedetails.MovieDetails
import joe.barker.local.moviedetails.MovieDetailsLocalImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovieDetailsLocalShould {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val database = Room.inMemoryDatabaseBuilder(
        context, MovieDatabase::class.java).build()
    private val dao = database.movieDetailsDao()

    private val id: Long = 1
    private val title = "title"
    private val tagline = "tagline"
    private val overview = "overview"
    private val releaseDate = "2020-01-02"
    private val movieDetails = MovieDetails(id, title, tagline, overview, releaseDate)
    private val movieDetailsResponse = MovieDetailsResponse(id, title, tagline, overview, releaseDate)
    private val local = MovieDetailsLocalImpl(database)

    @Test
    fun insertMovieDetailsToDatabase(){
        local.insert(movieDetailsResponse)
        assertEquals(movieDetails, dao.findById(id))
    }

    @Test
    fun getMovieById(){
        dao.insertAll(movieDetails)
        assertEquals(movieDetailsResponse, local.getMovie(id))
    }

    @Test
    fun getNullIfMovieDoesntExist(){
        assertEquals(null, local.getMovie(id))
    }
}