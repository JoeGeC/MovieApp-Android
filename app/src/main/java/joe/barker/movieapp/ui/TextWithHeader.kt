package joe.barker.movieapp.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun TextWithHeader(header: String, body: String) {
    Text(
        text = header,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = MaterialTheme.colors.primary,
    )
    Text(
        text = body,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = MaterialTheme.colors.primary,
    )
}