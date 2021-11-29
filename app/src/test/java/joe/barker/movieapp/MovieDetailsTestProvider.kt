package joe.barker.movieapp

import joe.barker.domain.entity.MovieDetails
import java.time.LocalDate

class MovieDetailsTestProvider(){
    companion object{
        const val id1: Long = 1
        const val title1 = "title1"
        private val releaseDate1 = LocalDate.of(2021, 1, 2)
        const val releaseYear1 = "2021"
        const val tagline1 = "tagline1"
        const val overview1 = "overview1"
        const val posterPath1 = "poster1.jpg"
        const val score1 = 0.1f
        const val backdropPath1 = "backdropPath1.jpg"
        val movie1 = MovieDetails(id1, title1, releaseDate1, tagline1, overview1, posterPath1, score1, backdropPath1)

        const val id2: Long = 2
        const val title2 = "title2"
        private val releaseDate2 = LocalDate.of(2022, 1, 2)
        const val releaseYear2 = "2022"
        const val tagline2 = "tagline2"
        const val overview2 = "overview2"
        const val posterId2 = "poster2"
        const val score2 = 0.2f
        const val backdropPath2 = "backdropPath2.jpg"
        val movie2 = MovieDetails(id2, title2, releaseDate2, tagline2, overview2, posterId2, score2, backdropPath2)
    }
}
