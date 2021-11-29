package joe.barker.movieapp.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.skydoves.landscapist.glide.GlideImage
import joe.barker.movieapp.extension.toImageUrl

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
        painter = rememberImagePainter(backdropPath.toImageUrl()),
        contentDescription = "Backdrop",
        modifier = Modifier.width(400.dp)
    )
}

@Composable
private fun MoviePoster(posterPath: String?) {
    if(posterPath == null) return
    GlideImage(imageModel = posterPath.toImageUrl(),
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
    Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)) {
        TitleText(details.title, details.releaseYear)
        TaglineText(details.tagline)
        OverviewLabelText()
        OverviewText(details.overview)
        OverviewText(details.overview)
        OverviewText(details.overview)
        OverviewText(details.overview)
        OverviewText(details.overview)
        OverviewText(details.overview)
        OverviewText(details.overview)
        OverviewText(details.overview)
    }
}

@Composable
private fun TitleText(title: String, releaseYear: String) {
    Text(
        text = "$title ($releaseYear)",
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary
    )
}

@Composable
private fun TaglineText(tagline: String) {
    Text(
        text = tagline,
        fontSize = 12.sp,
        fontStyle = FontStyle.Italic,
        color = MaterialTheme.colors.primary
    )
}

@Composable
private fun OverviewLabelText() {
    Text(
        text = "Overview",
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = MaterialTheme.colors.primary
    )
}

@Composable
private fun OverviewText(overview: String) {
    Text(
        text = overview,
        fontSize = 12.sp,
        color = MaterialTheme.colors.primary
    )
}

