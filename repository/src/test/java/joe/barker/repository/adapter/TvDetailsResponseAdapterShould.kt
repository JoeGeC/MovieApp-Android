package joe.barker.repository.adapter

import joe.barker.domain.entity.MediaDetails
import joe.barker.domain.entity.MediaType
import joe.barker.repository.MediaDetailsRepoTestProvider
import joe.barker.repository.response.TvDetailsResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TvDetailsResponseAdapterShould {
    @Test
    fun `Convert tv details response to movie details`(){
        Assertions.assertEquals(
            MediaDetailsRepoTestProvider.tvDetails,
            MediaDetailsRepoTestProvider.tvDetailsResponse.convert()
        )
    }

    @Test
    fun `Convert null tv details response to movie details`(){
        val releaseDate = LocalDate.of(0, 1, 1)
        val tvDetailsResponse = TvDetailsResponse(null, null, null, null, null, null, null, null)
        val expected = MediaDetails(-1, "", releaseDate, "", "", "", 0f, "", MediaType.Tv)

        Assertions.assertEquals(expected, tvDetailsResponse.convert())
    }
}