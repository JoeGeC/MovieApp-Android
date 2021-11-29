package joe.barker.repository.response


data class MovieDetailsResponse(
    val id: Long?,
    val title: String?,
    val tagline: String?,
    val overview: String?,
    val release_date: String?,
    val poster_path: String?,
    val vote_average: Float?,
    val backdrop_path: String?
)