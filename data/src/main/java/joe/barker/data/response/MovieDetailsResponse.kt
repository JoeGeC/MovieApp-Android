package joe.barker.data.response


data class MovieDetailsResponse(
    val id: Long?,
    val title: String?,
    val tagline: String?,
    val overview: String?,
    val release_date: String?
)