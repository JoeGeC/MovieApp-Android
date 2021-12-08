package joe.barker.movieapp.popular

import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
import joe.barker.movieapp.config
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PopularMoviesViewModel(
    private val movieUseCase: PopularMoviesUseCase = config.popularMoviesUseCase,
) : PopularViewModel() {

    fun fetchPopularMovies(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        fetchFrom({ movieUseCase.getPopularMovies() }, dispatcher)
    }
}
