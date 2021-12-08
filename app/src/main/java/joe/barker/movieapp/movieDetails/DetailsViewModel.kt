package joe.barker.movieapp.movieDetails

import androidx.lifecycle.ViewModel
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.movieapp.job
import joe.barker.movieapp.popular.convert
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class DetailsViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
    lateinit var model: MediaDetailsModel

    protected fun fetchFrom(
        useCaseCall: suspend () -> Either<MediaDetails?, ErrorEntity?>,
        dispatcher: CoroutineDispatcher
    ) {
        job( {
            _isLoading.value = true
            val result = useCaseCall.invoke()
            if (result.isSuccess) model = result.body!!.convert()
            else _error.value = true
            _isLoading.value = false
        }, dispatcher)
    }
}
