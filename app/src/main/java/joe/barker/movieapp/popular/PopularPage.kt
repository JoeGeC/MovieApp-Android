package joe.barker.movieapp.popular

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import joe.barker.movieapp.ui.ErrorUi
import joe.barker.movieapp.ui.LoadingUi
import joe.barker.movieapp.ui.MediaList
import joe.barker.movieapp.ui.WelcomeUi

@ExperimentalAnimationApi
@Composable
fun PopularPage(navController: NavHostController) {
    val viewModel = viewModel<PopularViewModel>()
    LaunchedEffect(Unit) { viewModel.fetchMovies() }
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    Column {
        WelcomeUi()
        when {
            isLoading -> PopularUi(viewModel) { LoadingUi() }
            isError -> PopularUi(viewModel) { ErrorUi() }
            else -> PopularUi(viewModel) { MediaList(viewModel, navController) }
        }
    }
}