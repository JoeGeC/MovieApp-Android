package joe.barker.movieapp.popularMovies

import androidx.lifecycle.ViewModel
import joe.barker.domain.entity.MovieDetails
import joe.barker.domain.popular.PopularMoviesUseCase
import joe.barker.movieapp.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PopularMoviesViewModel(
    private val useCase: PopularMoviesUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
    lateinit var model: List<PopularMovieModel>

    fun fetchPopularMovies(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        job( {
            _isLoading.value = true
            val result = useCase.fetchPopularMovies()
            if (result.isSuccess) model = result.body!!.convert()
            else _error.value = true
            _isLoading.value = false
        }, dispatcher)
    }
}

private fun List<MovieDetails>.convert(): List<PopularMovieModel> = this.map{
    PopularMovieModel(
        it.id,
        it.title,
        it.releaseDate.year.toString(),
        it.score,
        it.posterId
    )
}
