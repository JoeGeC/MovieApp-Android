package joe.barker.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import joe.barker.movieapp.page.MovieDetailsPage
import joe.barker.movieapp.popularMovies.PopularMovieModel
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
            PopularPage()
        }
        composable("movieDetails") {
            MovieDetailsPage()
        }
    }
}

@Composable
fun PopularPage() {
    val movies = listOf(
        PopularMovieModel(
            580489,
            "Venom: Let There Be Carnage",
            "15 Oct 2021",
            7.2f,
            "https://image.tmdb.org/t/p/w500/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg"
        )
    )
    LazyRow{
        items(movies){ movie ->
            Column(modifier = Modifier.width(160.dp)) {
                Box{
                    Image(
                        painter = rememberImagePainter(movie.posterUrl),
                        contentDescription = "Movie poster",
                        modifier = Modifier
                            .width(160.dp)
                            .clip(RoundedCornerShape(10.dp)),

                    )
                    RatingCircle(movie.voteAverage)
                }
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Text(
                    text = movie.releaseDate,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    }
}

@Composable
private fun RatingCircle(rating: Float) {
    Box(contentAlignment= Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .background(Color.Black, shape = CircleShape)){
        CircularProgressIndicator(
            progress = rating / 10,
            color = percentageCircleColor(rating),
        )
        Text(
            text = "${rating.asPercentage()}%",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp)
        )
    }
}

fun Float.asPercentage(): String = (this * 10).toInt().toString()

fun percentageCircleColor(percentage: Float): Color {
    if(percentage < 4) return Color.Red
    if(percentage < 7) return Color.Yellow
    return Color.Green
}

@Preview
@Composable
private fun RatingCircle() {
    RatingCircle(7.0f)
}