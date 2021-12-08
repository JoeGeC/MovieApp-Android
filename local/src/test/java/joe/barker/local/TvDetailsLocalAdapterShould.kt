package joe.barker.local

import joe.barker.local.movieDetails.MovieDetails
import joe.barker.local.movieDetails.convert
import joe.barker.local.tvDetails.TvDetails
import joe.barker.local.tvDetails.convert
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.TvDetailsResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TvDetailsLocalAdapterShould {
    private val id: Long = 1
    private val title = "title"
    private val tagline = "tagline"
    private val overview = "overview"
    private val releaseDate = "2020-01-02"
    private val posterPath = "posterpath.png"
    private val voteAverage = 1.1f
    private val backdropPath = "backdropPath.png"
    private val tvDetailsResponse = TvDetailsResponse(id, title, tagline, overview, releaseDate, posterPath, voteAverage, backdropPath)
    private val tvDetails = TvDetails(id, title, tagline, overview, releaseDate, posterPath, voteAverage, backdropPath)

    @Test
    fun `Convert movie details response to movie details`(){
        Assertions.assertEquals(tvDetails, tvDetailsResponse.convert())
    }

    @Test
    fun `Convert movie details to movie details response`(){
        Assertions.assertEquals(tvDetailsResponse, tvDetails.convert())
    }
}