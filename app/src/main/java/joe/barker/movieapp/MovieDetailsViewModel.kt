package joe.barker.movieapp

import androidx.lifecycle.ViewModel
import joe.barker.domain.entity.MovieDetails
import joe.barker.domain.movieDetails.MovieDetailsUseCase
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
    private var details: MovieDetails? = null
    val title: String get() = details?.title ?: ""
    val releaseYear: String get() = details?.releaseDate?.year?.toString() ?: ""
    val tagline: String get() = details?.tagline ?: ""
    val overview: String get() = details?.overview ?: ""

    fun getMovieDetailsOf(movieId: Long, dispatcher: CoroutineDispatcher = Dispatchers.IO){
        job( {
            _isLoading.value = true
            val result = useCase.getMovieDetailsOf(movieId)
            if (result.isSuccess) details = result.body
            else _error.value = true
            _isLoading.value = false
        }, dispatcher)
    }
}