package joe.barker.movieapp.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.skydoves.landscapist.glide.GlideImage
import joe.barker.movieapp.R
import joe.barker.movieapp.extension.toImageUrl
import joe.barker.movieapp.popularMovies.asPercentage
import joe.barker.movieapp.popularMovies.percentageCircleColor
import joe.barker.movieapp.ui.RatingCircle

@Preview
@Composable
fun MovieDetailsUi() {
    val model = MovieDetailsModel(1, "title", "1998", "tagline", "overview", "123.jpg", 1.1f, "123.jpg")
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
fun DetailsContent(model: MovieDetailsModel) {
    Box (modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(top = 200.dp)){
        Box(modifier = Modifier
            .matchParentSize()
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(20.dp)
            ))
        Row{
            MoviePoster(model.posterPath)
            MovieDetailsText(model)
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
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .width(150.dp)
    )
}

@Composable
private fun MovieDetailsText(details: MovieDetailsModel) {
    val ratingCircleModifier = Modifier
        .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
        .size(48.dp)
        .background(Color.Black, shape = CircleShape)
    Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)) {
        TitleText(details.title, details.releaseYear)
        UserScore(details, ratingCircleModifier)
        TaglineText(details.tagline)
        OverviewLabelText()
        OverviewText(details.overview)
    }
}

@Composable
private fun UserScore(details: MovieDetailsModel, ratingCircleModifier: Modifier) {
    Row{
        RatingCircle(details.score, ratingCircleModifier)
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
private fun TitleText(title: String, releaseYear: String) {
    Text(
        text = "$title ($releaseYear)",
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary,
        fontSize = 18.sp
    )
}

@Composable
private fun TaglineText(tagline: String) {
    if(tagline.isEmpty()) return
    Text(
        text = tagline,
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        color = MaterialTheme.colors.primary
    )
}

@Composable
private fun OverviewLabelText() {
    Text(
        text = "Overview",
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = MaterialTheme.colors.primary
    )
}

@Composable
private fun OverviewText(overview: String) {
    Text(
        text = overview,
        fontSize = 14.sp,
        color = MaterialTheme.colors.primary
    )
}

