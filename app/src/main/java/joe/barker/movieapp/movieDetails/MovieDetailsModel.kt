package joe.barker.movieapp.movieDetails

import joe.barker.domain.entity.MovieDetails

class MovieDetailsModel(
    val id: Long,
    val title: String,
    val releaseYear: String,
    val tagline: String,
    val overview: String,
    val posterPath: String,
    val score: Float,
    val backdropPath: String
) {
}

internal fun MovieDetails.convert(): MovieDetailsModel =
    MovieDetailsModel(
        id,
        title,
        releaseDate.year.toString(),
        tagline,
        overview,
        posterPath,
        score,
        backdropPath
    )