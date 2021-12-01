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
import androidx.compose.material.MaterialTheme
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
import joe.barker.movieapp.extension.toImageUrl
import joe.barker.movieapp.R
import joe.barker.movieapp.ui.RatingCircle

@Composable
fun PopularMoviesUi(movieList: List<PopularMovieModel>, navController: NavHostController) {
    Column {
        Text(text = "Popular Movies",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.primary)
        LazyRow {
            items(movieList) { movie ->
                MovieListItem(movie, navController)
            }
        }
    }
}

@Composable
private fun MovieListItem(movie: PopularMovieModel, navController: NavHostController) {
    Column(modifier = Modifier
        .width(180.dp)
        .padding(8.dp)
        .clickable(onClick = { navController.navigate("movieDetails/${movie.id}") })) {
        MoviePosterWithRating(movie)
        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp),
            color = MaterialTheme.colors.primary
        )
        Text(
            text = movie.releaseYear,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 12.dp),
        )
    }
}

@Composable
private fun MoviePosterWithRating(movie: PopularMovieModel) {
    Box {
        Image(
            painter = rememberImagePainter(
                data = movie.posterPath.toImageUrl(),
                builder = { placeholder(R.drawable.poster_placeholder) }
            ),
            contentDescription = "Movie poster",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .width(180.dp)
                .clip(RoundedCornerShape(10.dp)),
        )
        val ratingCircleModifier = Modifier
            .padding(end = 8.dp, top = 220.dp)
            .size(48.dp)
            .background(Color.Black, shape = CircleShape)
            .align(Alignment.TopEnd)
        RatingCircle(movie.score, ratingCircleModifier)
    }
}

fun Float.asPercentage(): String = (this * 10).toInt().toString()

fun percentageCircleColor(percentage: Float): Color {
    if (percentage < 4) return Color.Red
    if (percentage < 7) return Color.Yellow
    return Color.Green
}

