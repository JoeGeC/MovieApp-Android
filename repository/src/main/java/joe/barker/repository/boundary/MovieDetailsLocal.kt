package joe.barker.repository.boundary

import joe.barker.repository.response.MovieDetailsResponse

interface MovieDetailsLocal {
    fun insert(movieDetailsResponse: MovieDetailsResponse)
    fun getMovie(movieId: Long) : MovieDetailsResponse?
}