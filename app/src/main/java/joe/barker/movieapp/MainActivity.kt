package joe.barker.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import joe.barker.movieapp.ui.ErrorUi
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
            MovieAppTheme {
                SetContent()
            }
        }
    }

    @Composable
    private fun SetContent() {
        val isLoading by viewModel.isLoading.collectAsState()
        val isError by viewModel.error.collectAsState()
        when {
            isLoading -> LoadingUi()
            isError -> ErrorUi()
            else -> MovieDetailsUi(viewModel)
        }
    }
}