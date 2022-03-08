package joe.barker.movieapp.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import joe.barker.movieapp.R
import joe.barker.movieapp.extension.toImageUrl
import joe.barker.movieapp.popular.PopularListItemModel
import joe.barker.movieapp.search.AutoCompleteBox
import joe.barker.movieapp.search.SearchBar

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun WelcomeUi(imagePath: String? = null) {
    Box(modifier = Modifier.fillMaxWidth(),){
        WelcomeBackdrop(imagePath)
        Column(modifier = Modifier.padding(16.dp)) {
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
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            val mediaItems = listOf(
                PopularListItemModel(1, "Hello", "1998", 10f, "", "", "Movie"),
                PopularListItemModel(2, "Hello Again", "1999", 1f, "", "", "Movie"),
                PopularListItemModel(3, "Again", "2000", 2f, "", "", "Movie"),
            )
            AutoCompleteSearchBar(mediaItems = mediaItems)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun WelcomeBackdrop(backdropPath: String?) {
    if(backdropPath == null) return
    Image(
        painter = rememberImagePainter(
            data = backdropPath.toImageUrl(),
            builder = { placeholder(R.drawable.backdrop_placeholder)}
        ),
        contentDescription = "Backdrop",
        modifier = Modifier.height(200.dp),
        colorFilter = ColorFilter.tint(
            color = Color(0xFF004bad),
            blendMode = BlendMode.Softlight
        ),
        contentScale = ContentScale.Crop
    )
}


@ExperimentalAnimationApi
@Composable
fun AutoCompleteSearchBar(mediaItems: List<PopularListItemModel>) {
    AutoCompleteBox(
        items = mediaItems,
        itemContent = { person ->
            MediaAutoCompleteItem(person)
        }
    ) {
        var value by remember { mutableStateOf("") }
        val view = LocalView.current

        onItemSelected { mediaItem ->
            value = mediaItem.title
            filter(value)
            view.clearFocus()
        }

        SearchBar(
            value = value,
            label = "Search TV and Movies",
            onDoneActionClick = {
                view.clearFocus()
            },
            onClearClick = {
                value = ""
                filter(value)
                view.clearFocus()
            },
            onFocusChanged = { focusState ->
                isSearching = focusState.isFocused
            },
            onValueChanged = { query ->
                value = query
                filter(value)
            }
        )
    }
}

@Composable
fun MediaAutoCompleteItem(mediaItem: PopularListItemModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = mediaItem.title, color = MaterialTheme.colors.primary)
        Text(text = mediaItem.releaseYear, color = MaterialTheme.colors.primary)
    }
}