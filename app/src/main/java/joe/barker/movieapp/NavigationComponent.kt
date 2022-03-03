package joe.barker.movieapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import joe.barker.movieapp.details.MediaDetailsPage
import joe.barker.movieapp.details.MovieDetailsViewModel
import joe.barker.movieapp.details.TvDetailsViewModel
import joe.barker.movieapp.popular.PopularPage

@Composable
fun NavigationComponent() {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {    }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "popular"
    ) {
        composable("popular") {
            PopularPage(navController)
        }
        composable(
            route = "movieDetails/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.LongType })
        ) { backStackEntry ->
            val viewModel = viewModel<MovieDetailsViewModel>(viewModelStoreOwner = viewModelStoreOwner)
            MediaDetailsPage(backStackEntry.arguments?.getLong("movieId"), viewModel)
        }
        composable(
            route = "tvDetails/{tvShowId}",
            arguments = listOf(navArgument("tvShowId") { type = NavType.LongType })
        ) { backStackEntry ->
            val viewModel = viewModel<TvDetailsViewModel>(viewModelStoreOwner = viewModelStoreOwner)
            MediaDetailsPage(backStackEntry.arguments?.getLong("tvShowId"), viewModel)
        }
    }
}