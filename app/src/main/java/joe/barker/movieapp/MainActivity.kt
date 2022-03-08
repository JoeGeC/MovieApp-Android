package joe.barker.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import coil.annotation.ExperimentalCoilApi
import joe.barker.movieapp.ui.theme.MovieAppTheme

@ExperimentalCoilApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme {
                NavigationComponent()
            }
        }
    }
}