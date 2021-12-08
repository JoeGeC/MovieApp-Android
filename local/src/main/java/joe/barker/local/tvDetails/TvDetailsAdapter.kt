package joe.barker.local.tvDetails

import joe.barker.repository.response.TvDetailsResponse

fun TvDetailsResponse.convert() = TvDetails(
    this.id!!,
    this.name,
    this.tagline,
    this.overview,
    this.first_air_date,
    this.poster_path,
    this.vote_average,
    this.backdrop_path
)

fun TvDetails.convert() = TvDetailsResponse (
    this.id,
    this.name,
    this.tagline,
    this.overview,
    this.first_air_date,
    this.poster_path,
    this.vote_average,
    this.backdrop_path
)