package joe.barker.movieapp

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.movieapp.movieDetails.MovieDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class MovieDetailsViewModelShould {
    @Test
    fun `Get movie details`() {
        val result = Either.Success(MovieTestProvider.movie1)
        val useCase = mock<MovieDetailsUseCase>{
            onBlocking { getMovieDetailsOf(MovieTestProvider.id1) }.doReturn(result)
        }
        val viewModel = MovieDetailsViewModel(useCase)

        runBlocking { viewModel.fetchMovieDetailsOf(MovieTestProvider.id1, Dispatchers.Unconfined) }

        assertMovieDetails(viewModel)
        assertFalse(viewModel.error.value)
    }

    private fun assertMovieDetails(viewModel: MovieDetailsViewModel) {
        assertEquals(MovieTestProvider.id1, viewModel.model.id)
        assertEquals(MovieTestProvider.title1, viewModel.model.title)
        assertEquals(MovieTestProvider.releaseYear1, viewModel.model.releaseYear)
        assertEquals(MovieTestProvider.tagline1, viewModel.model.tagline)
        assertEquals(MovieTestProvider.overview1, viewModel.model.overview)
        assertEquals(MovieTestProvider.posterPath1, viewModel.model.posterPath)
        assertEquals(MovieTestProvider.score1, viewModel.model.score)
        assertEquals(MovieTestProvider.backdropPath1, viewModel.model.backdropPath)
    }

    @Test
    fun `Show error on failed movie details`() {
        val errorMessage = "error"
        val result = Either.Failure(ErrorEntity(errorMessage))
        val useCase = mock<MovieDetailsUseCase>{
            onBlocking { getMovieDetailsOf(MovieTestProvider.id1) }.doReturn(result)
        }
        val viewModel = MovieDetailsViewModel(useCase)

        runBlocking { viewModel.fetchMovieDetailsOf(MovieTestProvider.id1, Dispatchers.Unconfined) }

        assertTrue(viewModel.error.value)
    }
}