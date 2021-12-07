package joe.barker.repository.response

data class TvDetailsResponse(
    val id: Long?,
    val name: String?,
    val tagline: String?,
    val overview: String?,
    val first_air_date: String?,
    val poster_path: String?,
    val vote_average: Float?,
    val backdrop_path: String?
)