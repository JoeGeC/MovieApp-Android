package joe.barker.movieapp.details

import joe.barker.domain.boundary.useCase.TvDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher

class TvDetailsViewModel(private val useCase: TvDetailsUseCase) : DetailsViewModel() {

    override fun fetchDetailsOf(tvShowId: Long, dispatcher: CoroutineDispatcher) {
        fetchFrom({ useCase.getTvDetailsOf(tvShowId) }, dispatcher)
    }
}
