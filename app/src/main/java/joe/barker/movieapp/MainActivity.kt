package joe.barker.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import joe.barker.movieapp.details.MediaDetailsPage
import joe.barker.movieapp.details.MovieDetailsViewModel
import joe.barker.movieapp.details.TvDetailsViewModel
import joe.barker.movieapp.popular.PopularPage
import joe.barker.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MovieAppTheme {
                NavigationComponent(navController)
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
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
            MediaDetailsPage(backStackEntry.arguments?.getLong("movieId"), MovieDetailsViewModel())
        }
        composable(
            route = "tvDetails/{tvShowId}",
            arguments = listOf(navArgument("tvShowId") { type = NavType.LongType })
        ) { backStackEntry ->
            MediaDetailsPage(backStackEntry.arguments?.getLong("tvShowId"), TvDetailsViewModel())
        }
    }
}