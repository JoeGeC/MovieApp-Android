package joe.barker.local.movieDetails

import joe.barker.repository.response.MovieDetailsResponse

fun MovieDetailsResponse.convert() = MovieDetails(
    this.id!!,
    this.title,
    this.tagline,
    this.overview,
    this.release_date,
    this.poster_path,
    this.vote_average,
    this.backdrop_path
)

fun MovieDetails.convert() = MovieDetailsResponse (
    this.id,
    this.title,
    this.tagline,
    this.overview,
    this.release_date,
    this.poster_path,
    this.vote_average,
    this.backdrop_path
)