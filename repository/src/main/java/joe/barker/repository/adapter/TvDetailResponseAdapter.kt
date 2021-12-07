package joe.barker.repository.adapter

import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.response.TvDetailsResponse

fun TvDetailsResponse.convert() = MediaDetails(
    id ?: -1,
    name.orEmpty(),
    first_air_date.convertDate(),
    tagline.orEmpty(),
    overview.orEmpty(),
    poster_path.orEmpty(),
    vote_average ?: 0f,
    backdrop_path.orEmpty()
)