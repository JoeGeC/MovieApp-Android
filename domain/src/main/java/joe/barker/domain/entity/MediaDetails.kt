package joe.barker.domain.entity

import java.time.LocalDate

data class MediaDetails(
    val id: Long,
    val title: String,
    val releaseDate: LocalDate,
    val tagline: String,
    val overview: String,
    val posterPath: String,
    val score: Float,
    val backdropPath: String
) {
}