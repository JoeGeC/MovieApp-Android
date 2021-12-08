package joe.barker.movieapp.details

import androidx.lifecycle.ViewModel
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.movieapp.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class DetailsViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
    lateinit var model: MediaDetailsModel

    abstract fun fetchDetailsOf(movieId: Long, dispatcher: CoroutineDispatcher = Dispatchers.IO)

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
