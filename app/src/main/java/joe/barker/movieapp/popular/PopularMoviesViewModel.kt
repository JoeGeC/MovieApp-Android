package joe.barker.movieapp.popular

import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
import joe.barker.movieapp.config
import kotlinx.coroutines.CoroutineDispatcher

class PopularMoviesViewModel(
    private val useCase: PopularMoviesUseCase = config.popularMoviesUseCase,
) : PopularViewModel() {

    override fun fetchPopular(dispatcher: CoroutineDispatcher) =
        fetchFrom({ useCase.getPopularMovies() }, dispatcher)
}
