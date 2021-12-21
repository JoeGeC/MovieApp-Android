package joe.barker.movieapp.popular

import joe.barker.domain.boundary.useCase.PopularTvUseCase
import joe.barker.movieapp.config
import kotlinx.coroutines.CoroutineDispatcher

class PopularTvViewModel(
    private val useCase: PopularTvUseCase = config.popularTvUseCase
) : PopularViewModel() {

    override fun fetchPopular(dispatcher: CoroutineDispatcher) {
        fetchFrom({ useCase.getPopularTvShows() }, dispatcher)
    }
}