package joe.barker.repository.adapter

import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.domain.entity.MovieDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun MovieDetailsResponse.convert() = MovieDetails(
    id ?: -1,
    title.orEmpty(),
    convertDate(),
    tagline.orEmpty(),
    overview.orEmpty(),
    poster_path.orEmpty(),
    vote_average ?: 0f
)

private fun MovieDetailsResponse.convertDate(): LocalDate {
    return if(release_date != null)
        LocalDate.parse(this.release_date, DateTimeFormatter.ISO_DATE)
    else
        LocalDate.of(0, 1, 1)
}
