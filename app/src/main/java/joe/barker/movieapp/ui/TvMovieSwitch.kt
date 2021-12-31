package joe.barker.movieapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import joe.barker.movieapp.R

@Composable
fun TvMovieSwitch(isMovie: Boolean, modifier: Modifier, onClick: () -> Unit){
    val cornerSize = 20.dp
    Box(modifier
        .height(IntrinsicSize.Max)
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick)
    ){
        SwitchIcon(isMovie, cornerSize, Modifier.align(Alignment.CenterEnd))
        SwitchOptions(cornerSize)
    }
}

@Composable
private fun SwitchIcon(isMovie: Boolean, cornerSize: Dp, endAlignment: Modifier) {
    if (isMovie) Box(Modifier.switchIconModifier(cornerSize, 32.dp))
    else Box(Modifier.switchIconModifier(cornerSize, 17.dp).then(endAlignment))
}

private fun Modifier.switchIconModifier(cornerSize: Dp, padding: Dp) = composed {
    Modifier
        .background(
            color = colorResource(R.color.dark_blue),
            shape = RoundedCornerShape(cornerSize))
        .fillMaxHeight()
        .padding(horizontal = padding)
}

@Composable
private fun SwitchOptions(cornerSize: Dp) {
    Row(
        Modifier.border(
            width = 1.dp,
            color = colorResource(id = R.color.dark_blue),
            shape = RoundedCornerShape(cornerSize)
        )
    ) {
        Text(
            text = "Movies",
            color = colorResource(R.color.teal_200),
            modifier = Modifier
                .padding(6.dp)
        )
        Text(
            text = "TV",
            color = colorResource(R.color.teal_200),
            modifier = Modifier
                .padding(6.dp)
        )
    }
}
