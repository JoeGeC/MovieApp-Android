package joe.barker.local.moviedetails

import joe.barker.repository.response.MediaDetailsResponse

fun MediaDetailsResponse.convert() = MovieDetails(
    this.id!!,
    this.title,
    this.tagline,
    this.overview,
    this.release_date,
    this.poster_path,
    this.vote_average,
    this.backdrop_path
)

fun MovieDetails.convert() = MediaDetailsResponse (
    this.id,
    this.title,
    this.tagline,
    this.overview,
    this.release_date,
    this.poster_path,
    this.vote_average,
    this.backdrop_path
)