package joe.barker.movieapp.details

import joe.barker.domain.boundary.useCase.TvDetailsUseCase
import joe.barker.movieapp.config
import kotlinx.coroutines.CoroutineDispatcher

class TvDetailsViewModel(
    private val useCase: TvDetailsUseCase = config.tvDetailsUseCase
) : DetailsViewModel() {

    override fun fetchDetailsOf(id: Long, dispatcher: CoroutineDispatcher) {
        fetchFrom({ useCase.getTvDetailsOf(id) }, dispatcher)
    }
}
