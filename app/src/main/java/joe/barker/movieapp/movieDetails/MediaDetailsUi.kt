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
fun MediaDetailsUi() {
    val model = MediaDetailsModel(1, "title", "1998", "29/05/1998", "tagline", "overview", "123.jpg", 1.1f, "123.jpg")
    MediaDetailsUi(model)
}

@Composable
fun MediaDetailsUi(model: MediaDetailsModel) {
    Surface(color = MaterialTheme.colors.background) {
        Backdrop(model.backdropPath)
        DetailsContent(model)
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
fun DetailsContent(media: MediaDetailsModel) {
    Box (modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(top = 180.dp)
    ) {
        Box(modifier = Modifier
            .matchParentSize()
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(20.dp)))
        Column(Modifier.padding(24.dp)) {
            Row(Modifier.padding(bottom = 16.dp)) {
                MediaPoster(media.posterPath)
                MediaDetailsSide(media)
            }
            TextWithHeader("Overview", media.overview)
        }
    }
}

@Composable
private fun MediaPoster(posterPath: String?) {
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
private fun MediaDetailsSide(media: MediaDetailsModel) {
    val ratingCircleModifier = Modifier
        .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
        .size(48.dp)
        .background(Color.Black, shape = CircleShape)
    Column {
        TitleText(media.title, media.releaseYear)
        ReleaseDateText(media.releaseDate)
        UserScore(media, ratingCircleModifier)
        TaglineText(media.tagline)
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
private fun UserScore(media: MediaDetailsModel, ratingCircleModifier: Modifier) {
    Row{
        RatingCircle(media.score, ratingCircleModifier)
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