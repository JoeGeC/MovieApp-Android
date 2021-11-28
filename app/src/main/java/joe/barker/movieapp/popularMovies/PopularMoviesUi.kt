package joe.barker.movieapp.popularMovies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter

@Composable
fun PopularMoviesPage(navController: NavHostController) {
    val movies = listOf(
        PopularMovieModel(
            580489,
            "Venom: Let There Be Carnage",
            "15 Oct 2021",
            7.2f,
            "rjkmN1dniUHVYAtwuV3Tji7FsDO"
        )
    )
    LazyRow {
        items(movies) { movie ->
            MovieListItem(movie, navController)
        }
    }
}

@Composable
private fun MovieListItem(movie: PopularMovieModel, navController: NavHostController) {
    Column(modifier = Modifier
        .width(180.dp)
        .padding(8.dp)
        .clickable(onClick = { navController.navigate("movieDetails/${movie.id}/${movie.posterId}")} )) {
        Box {
            Image(
                painter = rememberImagePainter(movie.posterId.toW500Url()),
                contentDescription = "Movie poster",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .width(180.dp)
                    .clip(RoundedCornerShape(10.dp)),
            )
            Box( //Rating circle
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(48.dp)
                    .background(Color.Black, shape = CircleShape)
                    .align(Alignment.BottomEnd)
            ) {
                CircularProgressIndicator(
                    progress = movie.voteAverage / 10,
                    color = percentageCircleColor(movie.voteAverage),
                    strokeWidth = 2.dp
                )
                Text(
                    text = "${movie.voteAverage.asPercentage()}%",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
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

fun Float.asPercentage(): String = (this * 10).toInt().toString()

fun percentageCircleColor(percentage: Float): Color {
    if (percentage < 4) return Color.Red
    if (percentage < 7) return Color.Yellow
    return Color.Green
}

fun String.toW500Url() = "https://image.tmdb.org/t/p/w500/${this}.jpg"