package joe.barker.data.boundary

import joe.barker.data.response.MovieDetailsResponse

interface MovieDetailsLocal {
    fun insertAll(movieDetailsResponse: MovieDetailsResponse)
    fun getMovie(movieId: Long) : MovieDetailsResponse?
}