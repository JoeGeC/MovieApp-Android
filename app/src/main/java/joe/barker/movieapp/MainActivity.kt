package joe.barker.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import joe.barker.movieapp.ui.LoadingUi
import joe.barker.movieapp.ui.MovieDetailsUi
import joe.barker.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieDetailsOf(550)
        setContent {
            val isLoading by viewModel.isLoading.collectAsState()
            MovieAppTheme {
                setContent(isLoading)
            }
        }
    }

    @Composable
    private fun setContent(isLoading: Boolean) {
        when {
            isLoading -> LoadingUi()
            else -> MovieDetailsUi(viewModel)
        }
    }
}