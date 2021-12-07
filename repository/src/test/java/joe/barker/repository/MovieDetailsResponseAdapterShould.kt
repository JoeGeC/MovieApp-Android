package joe.barker.repository

import joe.barker.repository.adapter.convert
import joe.barker.repository.response.MediaDetailsResponse
import joe.barker.domain.entity.MediaDetails
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieDetailsResponseAdapterShould {
    @Test
    fun `Convert movie details response to movie details`(){
        assertEquals(MediaDetailsRepoTestProvider.mediaDetails, MediaDetailsRepoTestProvider.mediaDetailsResponse.convert())
    }

    @Test
    fun `Convert null movie details response to movie details`(){
        val releaseDate = LocalDate.of(0, 1, 1)
        val movieDetailsResponse = MediaDetailsResponse(null, null, null, null, null, null, null, null)
        val expected = MediaDetails(-1, "", releaseDate, "", "", "", 0f, "")

        assertEquals(expected, movieDetailsResponse.convert())
    }
}