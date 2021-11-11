package joe.barker.local.moviedetails

import joe.barker.data.boundary.MovieDetailsLocal
import joe.barker.data.response.MovieDetailsResponse
import joe.barker.local.MovieDatabase

class MovieDetailsLocalImpl(private val database: MovieDatabase) : MovieDetailsLocal {
    override fun insertAll(movieDetailsResponse: MovieDetailsResponse) {
        database.movieDetailsDao().insertAll(movieDetailsResponse.convert())
    }

    override fun getMovie(movieId: Long) : MovieDetailsResponse? {
        val result = database.movieDetailsDao().findById(movieId)
        return if(result == null) null
        else result.convert()
    }
}

private fun MovieDetailsResponse.convert() = MovieDetails(
    this.id!!,
    this.title,
    this.tagline,
    this.overview,
    this.release_date
)
