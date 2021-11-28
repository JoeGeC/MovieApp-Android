package joe.barker.repository

import joe.barker.domain.entity.MovieDetails
import joe.barker.repository.response.MovieDetailsResponse
import java.time.LocalDate

class MovieDetailsRepoTestProvider {
    companion object{
        internal const val movieId: Long = 1
        private const val movieTitle = "Title"
        private const val movieTagline = "Tagline"
        private const val movieOverview = "Overview"
        private val movieReleaseDate = LocalDate.of(2020, 1, 2)
        private const val movieReleaseDateString = "2020-01-02"
        private const val posterId = "posterId"
        private const val score = 1.1f
        internal val movieDetailsResponse = MovieDetailsResponse(movieId, movieTitle, movieTagline, movieOverview, movieReleaseDateString, posterId, score)
        internal val movieDetails = MovieDetails(movieId, movieTitle, movieReleaseDate, movieTagline, movieOverview, posterId, score)
    }
}