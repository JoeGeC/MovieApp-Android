package joe.barker.domain.entity

import java.time.LocalDate

data class MovieDetails(
    val id: Long,
    val title: String,
    val releaseDate: LocalDate,
    val tagline: String,
    val overview: String,
    val posterId: String,
    val score: Float
) {
}