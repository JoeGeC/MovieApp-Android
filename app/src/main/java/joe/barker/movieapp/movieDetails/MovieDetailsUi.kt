package joe.barker.movieapp.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import joe.barker.movieapp.popularMovies.toW500Url

@Composable
fun MovieDetailsUi(model: MovieDetailsModel, posterId: String?) {
    Surface(color = MaterialTheme.colors.background) {
        Row{
            MoviePoster(posterId)
            MovieDetailsText(model)
        }
    }
}

@Composable
private fun MoviePoster(posterId: String?) {
    if(posterId == null) return
    Image(
        painter = rememberImagePainter(posterId.toW500Url()),
        contentDescription = "Movie Poster",
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(150.dp)
            .padding(16.dp),
    )
}

@Composable
private fun MovieDetailsText(details: MovieDetailsModel) {
    Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)) {
        TitleText(details.title, details.releaseYear)
        TaglineText(details.tagline)
        OverviewLabelText()
        OverviewText(details.overview)
    }
}

@Composable
private fun OverviewText(overview: String) {
    Text(
        text = overview,
        fontSize = 12.sp
    )
}

@Preview
@Composable
private fun OverviewText() {
    OverviewText("Overview")
}

@Preview
@Composable
private fun OverviewLabelText() {
    Text(
        text = "Overview",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun TaglineText(tagline: String) {
    Text(
        text = tagline,
        fontSize = 12.sp,
        fontStyle = FontStyle.Italic
    )
}

@Preview
@Composable
private fun TaglineText() {
    TaglineText("Tagline")
}

@Composable
private fun TitleText(title: String, releaseYear: String) {
    Text(
        text = "$title ($releaseYear)",
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
private fun TitleText() {
    TitleText("Title", "2020")
}