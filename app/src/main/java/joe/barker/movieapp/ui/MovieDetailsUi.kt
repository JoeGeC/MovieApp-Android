package joe.barker.movieapp.ui

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import joe.barker.movieapp.viewModel.MovieDetailsViewModel
import joe.barker.movieapp.R

@Composable
fun MovieDetailsUi(viewModel: MovieDetailsViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        Row{
            MoviePoster()
            MovieDetailsText(viewModel)
        }
    }
}

@Preview
@Composable
private fun MoviePoster() {
    Image(
        painter = painterResource(R.drawable.fight_club),
        contentDescription = "Movie Poster",
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(150.dp)
            .padding(16.dp),
    )
}

@Composable
private fun MovieDetailsText(viewModel: MovieDetailsViewModel) {
    Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)) {
        TitleText(viewModel.title, viewModel.releaseYear)
        TaglineText(viewModel.tagline)
        OverviewLabelText()
        OverviewText(viewModel.overview)
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