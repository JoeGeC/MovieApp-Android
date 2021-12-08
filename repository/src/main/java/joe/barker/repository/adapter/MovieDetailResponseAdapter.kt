package joe.barker.repository.adapter

import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.domain.entity.MediaDetails
import joe.barker.domain.entity.MediaType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun MovieDetailsResponse.convert() = MediaDetails(
    id ?: -1,
    title.orEmpty(),
    release_date.convertDate(),
    tagline.orEmpty(),
    overview.orEmpty(),
    poster_path.orEmpty(),
    vote_average ?: 0f,
    backdrop_path.orEmpty(),
    MediaType.Movie
)