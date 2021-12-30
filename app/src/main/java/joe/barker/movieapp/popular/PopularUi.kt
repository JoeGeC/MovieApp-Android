package joe.barker.movieapp.popular

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import joe.barker.movieapp.ui.TvMovieSwitch

@Composable
fun PopularUi(viewModel: PopularViewModel, content: @Composable () -> Unit) {
    val isMovies by viewModel.isMovies.collectAsState()
    Column {
        Header(isMovies, viewModel)
        content()
    }
}

@Composable
private fun Header(
    isMovies: Boolean,
    viewModel: PopularViewModel
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "What's Popular",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = MaterialTheme.colors.primary
        )
        TvMovieSwitch(isMovies, Modifier.align(Alignment.CenterEnd)) { viewModel.onSwitch() }
    }
}