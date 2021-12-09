package joe.barker.movieapp.details

import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.movieapp.config
import kotlinx.coroutines.CoroutineDispatcher

class MovieDetailsViewModel(
    private val useCase: MovieDetailsUseCase = config.movieDetailsUseCase
) : DetailsViewModel() {

    override fun fetchDetailsOf(id: Long, dispatcher: CoroutineDispatcher){
        if(model != null) return
        fetchFrom({ useCase.getMovieDetailsOf(id) }, dispatcher)
    }
}

