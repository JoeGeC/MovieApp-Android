package joe.barker.repository.boundary.local

import joe.barker.repository.response.MovieDetailsResponse

interface MovieDetailsLocal {
    fun insert(movieDetailsResponse: MovieDetailsResponse)
    fun getMovie(movieId: Long) : MovieDetailsResponse?
}