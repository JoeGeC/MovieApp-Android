package joe.barker.movieapp.popularMovies

import joe.barker.domain.entity.MovieDetails

class PopularMovieModel(
    val id: Long,
    val title: String,
    val releaseYear: String,
    val score: Float,
    val posterId: String
) {

}

internal fun List<MovieDetails>.convert(): List<PopularMovieModel> = this.map{
    PopularMovieModel(
        it.id,
        it.title,
        it.releaseDate.year.toString(),
        it.score,
        it.posterId
    )
}