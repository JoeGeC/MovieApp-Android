package joe.barker.repository

import joe.barker.domain.entity.MediaDetails
import joe.barker.domain.entity.MediaType
import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.TvDetailsResponse
import java.time.LocalDate

class MediaDetailsRepoTestProvider {
    companion object{
        internal const val id: Long = 1
        private const val title = "Title"
        private const val tagline = "Tagline"
        private const val overview = "Overview"
        private val releaseDate = LocalDate.of(2020, 1, 2)
        private const val releaseDateString = "2020-01-02"
        private const val posterPath = "posterId.jpg"
        private const val score = 1.1f
        private const val backdropPath = "backdropPath.jpg"
        internal val movieDetailsResponse = MovieDetailsResponse(id, title, tagline, overview, releaseDateString, posterPath, score, backdropPath)
        internal val movieDetails = MediaDetails(id, title, releaseDate, tagline, overview, posterPath, score, backdropPath, MediaType.Movie)
        internal val tvDetailsResponse = TvDetailsResponse(id, title, tagline, overview, releaseDateString, posterPath, score, backdropPath)
        internal val tvDetails = MediaDetails(id, title, releaseDate, tagline, overview, posterPath, score, backdropPath, MediaType.Tv)
    }
}