package joe.barker.movieapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeUi() {
    Box(
        modifier = Modifier
            .background(color = Color.Blue)
            .fillMaxWidth()
            .padding(16.dp),
    ){
        Column {
            Text(
                text = "Welcome.",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Millions of movies, TV shows and people to discover. Explore now.",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colors.primary
            )
        }
    }
}