package joe.barker.movieapp

import joe.barker.domain.entity.MediaDetails
import joe.barker.domain.entity.MediaType
import joe.barker.movieapp.details.MediaDetailsModel
import joe.barker.movieapp.popular.PopularListItemModel
import org.junit.jupiter.api.Assertions
import java.time.LocalDate

class MediaTestProvider {
    companion object{
        const val id1: Long = 1
        const val title1 = "title1"
        private val releaseDate1 = LocalDate.of(2021, 1, 2)
        const val releaseDateAsString1 = "02/01/2021"
        const val releaseYear1 = "2021"
        const val tagline1 = "tagline1"
        const val overview1 = "overview1"
        const val posterPath1 = "poster1.jpg"
        const val score1 = 0.1f
        const val backdropPath1 = "backdropPath1.jpg"
        val movieType = MediaType.Movie
        const val movieString = "movie"
        val tvType = MediaType.Tv
        const val tvString = "tv"
        val movieDetails1 = MediaDetails(id1, title1, releaseDate1, tagline1, overview1, posterPath1, score1, backdropPath1, movieType)
        val popularMovieModel1 = PopularListItemModel(id1, title1, releaseYear1, score1, posterPath1, movieString)
        val tvDetails1 = MediaDetails(id1, title1, releaseDate1, tagline1, overview1, posterPath1, score1, backdropPath1, tvType)
        val popularTvModel1 = PopularListItemModel(id1, title1, releaseYear1, score1, posterPath1, tvString)

        const val id2: Long = 2
        const val title2 = "title2"
        private val releaseDate2 = LocalDate.of(2022, 1, 2)
        const val releaseYear2 = "2022"
        const val tagline2 = "tagline2"
        const val overview2 = "overview2"
        const val posterPath2 = "poster2.jpg"
        const val score2 = 0.2f
        const val backdropPath2 = "backdropPath2.jpg"
        val movieDetails2 = MediaDetails(id2, title2, releaseDate2, tagline2, overview2, posterPath2, score2, backdropPath2, tvType)
        val popularMovieModel2 = PopularListItemModel(id2, title2, releaseYear2, score2, posterPath2, tvString)
        val tvDetails2 = MediaDetails(id2, title2, releaseDate2, tagline2, overview2, posterPath2, score2, backdropPath2, tvType)
        val popularTvModel2 = PopularListItemModel(id2, title2, releaseYear2, score2, posterPath2, tvString)

        fun assertMediaDetails(result: MediaDetailsModel) {
            Assertions.assertEquals(id1, result.id)
            Assertions.assertEquals(title1, result.title)
            Assertions.assertEquals(releaseYear1, result.releaseYear)
            Assertions.assertEquals(releaseDateAsString1, result.releaseDate)
            Assertions.assertEquals(tagline1, result.tagline)
            Assertions.assertEquals(overview1, result.overview)
            Assertions.assertEquals(posterPath1, result.posterPath)
            Assertions.assertEquals(score1, result.score)
            Assertions.assertEquals(backdropPath1, result.backdropPath)
        }
    }
}
