package joe.barker.local.moviedetails

import joe.barker.repository.boundary.local.MovieDetailsLocal
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.local.MovieDatabase

class MovieDetailsLocalImpl(private val database: MovieDatabase) : MovieDetailsLocal {
    override fun insert(movieDetailsResponse: MovieDetailsResponse) =
        database.movieDetailsDao().insertAll(movieDetailsResponse.convert())

    override fun getMovie(movieId: Long) : MovieDetailsResponse? =
        database.movieDetailsDao().findById(movieId)?.convert()
}


