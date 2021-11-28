package joe.barker.movieapp.movieDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import joe.barker.movieapp.ui.ErrorUi
import joe.barker.movieapp.ui.LoadingUi

@Composable
fun MovieDetailsPage(movieId: Long?, posterUrl: String?) {
    val viewModel = viewModel<MovieDetailsViewModel>()
    if(movieId != null) viewModel.getMovieDetailsOf(movieId)
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError -> ErrorUi()
        else -> MovieDetailsUi(viewModel.model, posterUrl)
    }
}