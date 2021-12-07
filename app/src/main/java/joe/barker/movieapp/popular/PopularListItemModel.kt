package joe.barker.movieapp.popular

import joe.barker.domain.entity.MediaDetails

class PopularListItemModel(
    val id: Long,
    val title: String,
    val releaseYear: String,
    val score: Float,
    val posterPath: String
)

internal fun List<MediaDetails>.convert(): List<PopularListItemModel> = this.map{
    PopularListItemModel(
        it.id,
        it.title,
        it.releaseDate.year.toString(),
        it.score,
        it.posterPath
    )
}