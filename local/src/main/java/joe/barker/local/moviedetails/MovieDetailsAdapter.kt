package joe.barker.local.moviedetails

import joe.barker.data.response.MovieDetailsResponse

fun MovieDetailsResponse.convert() = MovieDetails(
    this.id!!,
    this.title,
    this.tagline,
    this.overview,
    this.release_date
)

fun MovieDetails.convert() = MovieDetailsResponse (
    this.id,
    this.title,
    this.tagline,
    this.overview,
    this.release_date
)