package joe.barker.movieapp.movieDetails

import joe.barker.domain.entity.MediaDetails
import java.time.format.DateTimeFormatter

class MovieDetailsModel(
    val id: Long,
    val title: String,
    val releaseYear: String,
    val releaseDate: String,
    val tagline: String,
    val overview: String,
    val posterPath: String,
    val score: Float,
    val backdropPath: String
) {
}

internal fun MediaDetails.convert(): MovieDetailsModel =
    MovieDetailsModel(
        id,
        title,
        releaseDate.year.toString(),
        releaseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        tagline,
        overview,
        posterPath,
        score,
        backdropPath
    )