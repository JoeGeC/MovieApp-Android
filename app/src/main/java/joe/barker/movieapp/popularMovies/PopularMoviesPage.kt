package joe.barker.movieapp.popularMovies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import joe.barker.movieapp.ui.ErrorUi
import joe.barker.movieapp.ui.LoadingUi

@Composable
fun PopularMoviesPage(navController: NavHostController) {
    val viewModel = viewModel<PopularMoviesViewModel>()
    viewModel.fetchPopularMovies()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError -> ErrorUi()
        else -> PopularMoviesUi(viewModel.movieList!!, navController)
    }
}