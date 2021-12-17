package joe.barker.movieapp.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import joe.barker.movieapp.ui.ErrorUi
import joe.barker.movieapp.ui.LoadingUi

@Composable
fun PopularPage(navController: NavHostController) {
    val viewModel = viewModel<PopularMoviesViewModel>()
    LaunchedEffect(Unit, block = {
        viewModel.fetchPopularMovies()
    })
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError -> ErrorUi()
        else -> PopularUi(viewModel.popularList!!, navController)
    }
}