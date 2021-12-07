package joe.barker.movieapp.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.skydoves.landscapist.glide.GlideImage
import joe.barker.movieapp.R
import joe.barker.movieapp.extension.toImageUrl
import joe.barker.movieapp.ui.RatingCircle
import joe.barker.movieapp.ui.TextWithHeader

@Preview
@Composable
fun MovieDetailsUi() {
    val model = MovieDetailsModel(1, "title", "1998", "29/05/1998", "tagline", "overview", "123.jpg", 1.1f, "123.jpg")
    MovieDetailsUi(model)
}

@Composable
fun MovieDetailsUi(model: MovieDetailsModel) {
    Surface(color = MaterialTheme.colors.background) {
        Backdrop(model.backdropPath)
        DetailsContent(model)
    }
}

@Composable
fun DetailsContent(movie: MovieDetailsModel) {
    Box (modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(top = 200.dp)
    ) {
        Box(modifier = Modifier
            .matchParentSize()
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(20.dp)))
        Column(Modifier.padding(24.dp)) {
            Row(Modifier.padding(bottom = 16.dp)) {
                MoviePoster(movie.posterPath)
                MovieDetailsSide(movie)
            }
            TextWithHeader("Overview", movie.overview)
        }
    }
}

@Composable
private fun Backdrop(backdropPath: String?) {
    if(backdropPath == null) return
    Image(
        painter = rememberImagePainter(
            data = backdropPath.toImageUrl(),
            builder = { placeholder(R.drawable.backdrop_placeholder)}
        ),
        contentDescription = "Backdrop",
        modifier = Modifier.width(400.dp)
    )
}

@Composable
private fun MoviePoster(posterPath: String?) {
    if(posterPath == null) return
    GlideImage(imageModel = posterPath.toImageUrl(),
        placeHolder = ImageBitmap.imageResource(R.drawable.poster_placeholder),
        contentScale = ContentScale.FillWidth,
        contentDescription = "Movie Poster",
        modifier = Modifier
            .padding(end = 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .width(150.dp)
    )
}

@Composable
private fun MovieDetailsSide(movie: MovieDetailsModel) {
    val ratingCircleModifier = Modifier
        .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
        .size(48.dp)
        .background(Color.Black, shape = CircleShape)
    Column {
        TitleText(movie.title, movie.releaseYear)
        ReleaseDateText(movie.releaseDate)
        UserScore(movie, ratingCircleModifier)
        TaglineText(movie.tagline)
    }
}

@Composable
private fun TitleText(title: String, releaseYear: String) {
    Text(
        text = "$title ($releaseYear)",
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary,
        fontSize = 18.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ReleaseDateText(releaseDate: String) {
    Text(
        text = releaseDate,
        fontSize = 14.sp,
        color = MaterialTheme.colors.primary,
    )
}

@Composable
private fun UserScore(movie: MovieDetailsModel, ratingCircleModifier: Modifier) {
    Row{
        RatingCircle(movie.score, ratingCircleModifier)
        Text(
            text = "User\nScore",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun TaglineText(tagline: String) {
    if(tagline.isEmpty()) return
    Text(
        text = tagline,
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

