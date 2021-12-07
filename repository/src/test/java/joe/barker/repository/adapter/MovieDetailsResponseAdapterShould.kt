package joe.barker.repository.adapter

import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.MediaDetailsRepoTestProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieDetailsResponseAdapterShould {
    @Test
    fun `Convert movie details response to movie details`(){
        assertEquals(MediaDetailsRepoTestProvider.mediaDetails, MediaDetailsRepoTestProvider.movieDetailsResponse.convert())
    }

    @Test
    fun `Convert null movie details response to movie details`(){
        val releaseDate = LocalDate.of(0, 1, 1)
        val movieDetailsResponse = MovieDetailsResponse(null, null, null, null, null, null, null, null)
        val expected = MediaDetails(-1, "", releaseDate, "", "", "", 0f, "")

        assertEquals(expected, movieDetailsResponse.convert())
    }
}