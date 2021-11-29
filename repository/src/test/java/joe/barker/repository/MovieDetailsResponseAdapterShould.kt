package joe.barker.repository

import joe.barker.repository.adapter.convert
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.domain.entity.MovieDetails
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieDetailsResponseAdapterShould {
    @Test
    fun `Convert movie details response to movie details`(){
        assertEquals(MovieDetailsRepoTestProvider.movieDetails, MovieDetailsRepoTestProvider.movieDetailsResponse.convert())
    }

    @Test
    fun `Convert null movie details response to movie details`(){
        val releaseDate = LocalDate.of(0, 1, 1)
        val movieDetailsResponse = MovieDetailsResponse(null, null, null, null, null, null, null, null)
        val expected = MovieDetails(-1, "", releaseDate, "", "", "", 0f, "")

        assertEquals(expected, movieDetailsResponse.convert())
    }
}