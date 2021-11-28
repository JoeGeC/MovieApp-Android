package joe.barker.movieapp.movieDetails

import androidx.lifecycle.ViewModel
import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.movieapp.config
import joe.barker.movieapp.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieDetailsViewModel(
    private val useCase: MovieDetailsUseCase = config.movieDetailsUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
    lateinit var model: MovieDetailsModel

    fun fetchMovieDetailsOf(movieId: Long, dispatcher: CoroutineDispatcher = Dispatchers.IO){
        job( {
            _isLoading.value = true
            val result = useCase.getMovieDetailsOf(movieId)
            if (result.isSuccess) model = result.body!!.convert()
            else _error.value = true
            _isLoading.value = false
        }, dispatcher)
    }
}

