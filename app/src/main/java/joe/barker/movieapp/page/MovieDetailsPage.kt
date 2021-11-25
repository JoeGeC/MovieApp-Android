package joe.barker.movieapp.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import joe.barker.movieapp.viewModel.MovieDetailsViewModel
import joe.barker.movieapp.ui.ErrorUi
import joe.barker.movieapp.ui.LoadingUi
import joe.barker.movieapp.ui.MovieDetailsUi

@Composable
fun MovieDetailsPage() {
    val viewModel = viewModel<MovieDetailsViewModel>()
    viewModel.getMovieDetailsOf(550)
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError -> ErrorUi()
        else -> MovieDetailsUi(viewModel)
    }
}