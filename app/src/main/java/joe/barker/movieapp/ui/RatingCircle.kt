package joe.barker.movieapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import joe.barker.movieapp.popularMovies.asPercentage
import joe.barker.movieapp.popularMovies.percentageCircleColor

@Composable
fun RatingCircle(score: Float, modifier: Modifier){
    Box( //Rating circle
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            progress = score / 10,
            color = percentageCircleColor(score),
            strokeWidth = 2.dp
        )
        Text(
            text = "${score.asPercentage()}%",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            modifier = Modifier.padding(4.dp)
        )
    }
}