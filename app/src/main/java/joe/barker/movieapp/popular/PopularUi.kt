package joe.barker.movieapp.popular

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import joe.barker.movieapp.extension.toImageUrl
import joe.barker.movieapp.R
import joe.barker.movieapp.ui.RatingCircle
import joe.barker.movieapp.ui.TvMovieSwitch

@Composable
fun PopularUi(viewModel: PopularViewModel, navController: NavHostController) {
    val isMovies by viewModel.isMovies.collectAsState()
    Column() {
        Box(Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(text = "What's Popular",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary)
            TvMovieSwitch(isMovies, Modifier.align(Alignment.CenterEnd)) { viewModel.onSwitch() }
        }
        LazyRow {
            items(viewModel.popularList!!) { movie ->
                MovieListItem(movie, navController)
            }
        }
    }
}

@Composable
private fun MovieListItem(listItem: PopularListItemModel, navController: NavHostController) {
    Column(modifier = Modifier
        .width(180.dp)
        .padding(8.dp)
        .clickable(onClick = { navController.navigate("${listItem.type}Details/${listItem.id}") })) {
        MoviePosterWithRating(listItem)
        Text(
            text = listItem.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp),
            color = MaterialTheme.colors.primary
        )
        Text(
            text = listItem.releaseYear,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 12.dp),
        )
    }
}

@Composable
private fun MoviePosterWithRating(listItem: PopularListItemModel) {
    Box {
        MoviePoster(listItem)
        val ratingCircleModifier = Modifier
            .padding(end = 8.dp, top = 220.dp)
            .size(48.dp)
            .background(Color.Black, shape = CircleShape)
            .align(Alignment.TopEnd)
        RatingCircle(listItem.score, ratingCircleModifier)
    }
}

@Composable
private fun MoviePoster(listItem: PopularListItemModel) {
    Image(
        painter = rememberImagePainter(
            data = listItem.posterPath.toImageUrl(),
            builder = { placeholder(R.drawable.poster_placeholder) }
        ),
        contentDescription = "Movie poster",
        modifier = Modifier
            .padding(bottom = 16.dp)
            .width(180.dp)
            .clip(RoundedCornerShape(10.dp)),
    )
}

fun Float.asPercentage(): String = (this * 10).toInt().toString()

fun percentageCircleColor(percentage: Float): Color {
    if (percentage < 4) return Color.Red
    if (percentage < 7) return Color.Yellow
    return Color.Green
}

