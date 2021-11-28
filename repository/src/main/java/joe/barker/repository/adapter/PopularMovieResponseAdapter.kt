package joe.barker.repository.adapter

import joe.barker.domain.entity.MovieDetails
import joe.barker.repository.response.PopularMoviesResponse

fun PopularMoviesResponse?.convert(): List<MovieDetails>? =
    this?.results?.map { it.convert() }