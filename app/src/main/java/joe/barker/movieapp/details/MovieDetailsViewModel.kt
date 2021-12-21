package joe.barker.movieapp.details

import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.movieapp.config
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MovieDetailsViewModel(
    private val useCase: MovieDetailsUseCase = config.movieDetailsUseCase
) : DetailsViewModel() {

    override fun fetchDetailsOf(id: Long, dispatcher: CoroutineDispatcher) =
        fetchFrom({ useCase.getMovieDetailsOf(id) }, dispatcher)
}

