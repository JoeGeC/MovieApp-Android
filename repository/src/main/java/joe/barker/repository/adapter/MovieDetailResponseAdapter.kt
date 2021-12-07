package joe.barker.repository.adapter

import joe.barker.repository.response.MediaDetailsResponse
import joe.barker.domain.entity.MediaDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun MediaDetailsResponse.convert() = MediaDetails(
    id ?: -1,
    title.orEmpty(),
    convertDate(),
    tagline.orEmpty(),
    overview.orEmpty(),
    poster_path.orEmpty(),
    vote_average ?: 0f,
    backdrop_path.orEmpty()
)

private fun MediaDetailsResponse.convertDate(): LocalDate {
    return if(release_date != null)
        LocalDate.parse(this.release_date, DateTimeFormatter.ISO_DATE)
    else
        LocalDate.of(0, 1, 1)
}
