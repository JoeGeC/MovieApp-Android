package joe.barker.movieapp.popular

import joe.barker.domain.boundary.useCase.PopularTvUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PopularTvViewModel(
    private val useCase: PopularTvUseCase
) : BasePopularViewModel() {

    fun fetchPopularTvShows(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        fetchFrom({ useCase.getPopularTvShows() }, dispatcher)
    }
}