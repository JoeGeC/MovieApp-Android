package joe.barker.movieapp.popular

import androidx.lifecycle.ViewModel
import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
import joe.barker.domain.boundary.useCase.PopularTvUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.domain.useCase.PopularMoviesUseCaseImpl
import joe.barker.movieapp.config
import joe.barker.movieapp.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PopularViewModel(
    private val movieUseCase: PopularMoviesUseCase = config.popularMoviesUseCase,
    private val tvUseCase: PopularTvUseCase = config.popularTvUseCase,
) : ViewModel() {
    private val _isMovies = MutableStateFlow(true)
    val isMovies: StateFlow<Boolean> = _isMovies
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
    var popularList: List<PopularListItemModel>? = null

    fun onSwitch(){
        _isMovies.value = !_isMovies.value
        if(_isMovies.value) fetchMovies()
        else fetchTvShows()
    }

    fun fetchMovies(dispatcher: CoroutineDispatcher = Dispatchers.IO) =
        fetchFrom({ movieUseCase.getPopularMovies() }, dispatcher)

    fun fetchTvShows(dispatcher: CoroutineDispatcher = Dispatchers.IO) =
        fetchFrom({ tvUseCase.getPopularTvShows() }, dispatcher)

    private fun fetchFrom(
        useCaseCall: suspend () -> Either<List<MediaDetails>?, ErrorEntity?>,
        dispatcher: CoroutineDispatcher
    ) {
        job( {
            _isLoading.value = true
            val result = useCaseCall.invoke()
            if (result.isSuccess) popularList = result.body!!.convert()
            else _error.value = true
            _isLoading.value = false
        }, dispatcher)
    }
}