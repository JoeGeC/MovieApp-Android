package joe.barker.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

var ioCoroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

fun ViewModel.ioJob(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(ioCoroutineDispatcher) {
        block()
    }
}

fun ViewModel.job(block: suspend CoroutineScope.() -> Unit, coroutineDispatcher: CoroutineDispatcher): Job {
    return viewModelScope.launch(coroutineDispatcher) {
        block()
    }
}