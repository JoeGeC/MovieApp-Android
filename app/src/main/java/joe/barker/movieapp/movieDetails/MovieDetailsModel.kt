package joe.barker.movieapp.movieDetails

import joe.barker.domain.entity.MovieDetails

class MovieDetailsModel(
    val id: Long,
    val title: String,
    val releaseYear: String,
    val tagline: String,
    val overview: String
) {}

internal fun MovieDetails.convert(): MovieDetailsModel =
    MovieDetailsModel(
        id,
        title,
        releaseDate.year.toString(),
        tagline,
        overview)