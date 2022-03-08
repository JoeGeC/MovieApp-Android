package joe.barker.movieapp.popular

import joe.barker.domain.entity.MediaDetails
import joe.barker.movieapp.search.AutoCompleteEntity

data class PopularListItemModel(
    val id: Long,
    val title: String,
    val releaseYear: String,
    val score: Float,
    val posterPath: String,
    val backdropPath: String,
    val type: String
) : AutoCompleteEntity {
    override fun filter(query: String): Boolean = title.contains(query, true)
}

internal fun List<MediaDetails>.convert(): List<PopularListItemModel> = this.map{
    PopularListItemModel(
        it.id,
        it.title,
        it.releaseDate.year.toString(),
        it.score,
        it.posterPath,
        it.backdropPath,
        it.type.toString().lowercase()
    )
}