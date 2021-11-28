package joe.barker.movieapp.popularMovies

import androidx.lifecycle.ViewModel
import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
import joe.barker.movieapp.config
import joe.barker.movieapp.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PopularMoviesViewModel(
    private val useCase: PopularMoviesUseCase = config.popularMoviesUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
    lateinit var movieList: List<PopularMovieModel>

    fun fetchPopularMovies(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        job( {
            _isLoading.value = true
            val result = useCase.getPopularMovies()
            if (result.isSuccess) movieList = result.body!!.convert()
            else _error.value = true
            _isLoading.value = false
        }, dispatcher)
    }
}
