package joe.barker.movieapp.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import joe.barker.movieapp.ui.ErrorUi
import joe.barker.movieapp.ui.LoadingUi

@Composable
fun MediaDetailsPage(
    mediaId: Long?,
    viewModel: DetailsViewModel
) {
    LaunchedEffect(Unit, block = {
        viewModel.fetchDetailsOf(mediaId!!)
    })
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.error.collectAsState()
    when {
        isLoading -> LoadingUi()
        isError || viewModel.model == null -> ErrorUi()
        else -> MediaDetailsUi(viewModel.model!!)
    }
}