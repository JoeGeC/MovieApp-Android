package joe.barker.movieapp.movieDetails

import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.movieapp.config
import joe.barker.movieapp.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieDetailsViewModel(
    private val useCase: MovieDetailsUseCase = config.movieDetailsUseCase
) : DetailsViewModel() {

    fun fetchMovieDetailsOf(movieId: Long, dispatcher: CoroutineDispatcher = Dispatchers.IO){
        fetchFrom({ useCase.getMovieDetailsOf(movieId) }, dispatcher)
    }
}

