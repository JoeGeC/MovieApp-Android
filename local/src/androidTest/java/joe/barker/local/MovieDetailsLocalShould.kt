package joe.barker.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import joe.barker.repository.response.MediaDetailsResponse
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
    private val posterId = "2020-01-02"
    private val score = 1.1f
    private val backdropPath = "backdrop.png"
    private val movieDetails = MovieDetails(id, title, tagline, overview, releaseDate, posterId, score, backdropPath)
    private val movieDetailsResponse = MediaDetailsResponse(id, title, tagline, overview, releaseDate, posterId, score, backdropPath)
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