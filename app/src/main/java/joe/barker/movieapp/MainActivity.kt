package joe.barker.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import joe.barker.domain.entity.MovieDetails
import joe.barker.movieapp.page.MovieDetailsPage
import joe.barker.movieapp.ui.ErrorUi
import joe.barker.movieapp.ui.LoadingUi
import joe.barker.movieapp.ui.MovieDetailsUi
import joe.barker.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

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
            PopularPage()
        }
        composable("movieDetails") {
            MovieDetailsPage()
        }
    }
}

@Preview
@Composable
fun PopularPage() {
    val movieUrls = listOf("https://image.tmdb.org/t/p/w500/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg")
    LazyRow{
        items(movieUrls){ movieUrl ->
            Modifier.padding(16.dp)
            Image(
                painter = rememberImagePainter(movieUrl),
                contentDescription = "Movie poster",
                modifier = Modifier.width(128.dp)
            )

        }
    }
}
