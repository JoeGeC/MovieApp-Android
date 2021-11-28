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
import joe.barker.movieapp.extension.toW500Url

@Composable
fun PopularMoviesUi(movieList: List<PopularMovieModel>, navController: NavHostController) {
    LazyRow {
        items(movieList) { movie ->
            MovieListItem(movie, navController)
        }
    }
}

@Composable
private fun MovieListItem(movie: PopularMovieModel, navController: NavHostController) {
    Column(modifier = Modifier
        .width(180.dp)
        .padding(8.dp)
        .clickable(onClick = { navController.navigate("movieDetails/${movie.id}")} )) {
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
                    progress = movie.score / 10,
                    color = percentageCircleColor(movie.score),
                    strokeWidth = 2.dp
                )
                Text(
                    text = "${movie.score.asPercentage()}%",
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
            text = movie.releaseYear,
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

