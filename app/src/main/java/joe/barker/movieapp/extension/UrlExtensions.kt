package joe.barker.movieapp.extension

fun String.toImageUrl() = "https://image.tmdb.org/t/p/original/${this}"