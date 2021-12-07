package joe.barker.local.moviedetails

import joe.barker.repository.boundary.MovieDetailsLocal
import joe.barker.repository.response.MediaDetailsResponse
import joe.barker.local.MovieDatabase

class MovieDetailsLocalImpl(private val database: MovieDatabase) : MovieDetailsLocal {
    override fun insert(movieDetailsResponse: MediaDetailsResponse) =
        database.movieDetailsDao().insertAll(movieDetailsResponse.convert())

    override fun getMovie(movieId: Long) : MediaDetailsResponse? =
        database.movieDetailsDao().findById(movieId)?.convert()
}


