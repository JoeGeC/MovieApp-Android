package joe.barker.movieapp

import joe.barker.domain.entity.MediaDetails
import joe.barker.movieapp.popular.PopularListItemModel
import java.time.LocalDate

class MediaTestProvider(){
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
        val mediaDetails1 = MediaDetails(id1, title1, releaseDate1, tagline1, overview1, posterPath1, score1, backdropPath1)
        val popularListItemModel1 = PopularListItemModel(id1, title1, releaseYear1, score1, posterPath1)

        const val id2: Long = 2
        const val title2 = "title2"
        private val releaseDate2 = LocalDate.of(2022, 1, 2)
        const val releaseYear2 = "2022"
        const val tagline2 = "tagline2"
        const val overview2 = "overview2"
        const val posterPath2 = "poster2.jpg"
        const val score2 = 0.2f
        const val backdropPath2 = "backdropPath2.jpg"
        val mediaDetails2 = MediaDetails(id2, title2, releaseDate2, tagline2, overview2, posterPath2, score2, backdropPath2)
        val popularListItemModel2 = PopularListItemModel(id2, title2, releaseYear2, score2, posterPath2)
    }
}