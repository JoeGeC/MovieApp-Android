package joe.barker.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import joe.barker.config.Config
import joe.barker.domain.entity.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {
    private val useCase = Config.movieDetailsUseCase
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private var details: MovieDetails? = null
    val title: String get() = details!!.title
    val releaseYear: Int get() = details!!.releaseDate.year
    val tagline: String get() = details!!.tagline
    val overview: String get() = details!!.overview

    fun getMovieDetailsOf(movieId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            val result = useCase.getMovieDetailsOf(movieId)
            if(result.isSuccess) details = result.body
            _isLoading.value = false
        }
    }
}