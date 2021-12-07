package joe.barker.repository.boundary

import joe.barker.repository.response.MediaDetailsResponse

interface MovieDetailsLocal {
    fun insert(movieDetailsResponse: MediaDetailsResponse)
    fun getMovie(movieId: Long) : MediaDetailsResponse?
}