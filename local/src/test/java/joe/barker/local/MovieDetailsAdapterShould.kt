package joe.barker.local

import joe.barker.local.moviedetails.MovieDetails
import joe.barker.local.moviedetails.convert
import joe.barker.repository.response.MovieDetailsResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovieDetailsAdapterShould {
    private val id: Long = 1
    private val title = "title"
    private val tagline = "tagline"
    private val overview = "overview"
    private val releaseDate = "2020-01-02"
    private val movieDetailsResponse = MovieDetailsResponse(id, title, tagline, overview, releaseDate)
    private val movieDetails = MovieDetails(id, title, tagline, overview, releaseDate)

    @Test
    fun `Convert movie details response to movie details`(){
        assertEquals(movieDetails, movieDetailsResponse.convert())
    }

    @Test
    fun `Convert movie details to movie details response`(){
        assertEquals(movieDetailsResponse, movieDetails.convert())
    }
}