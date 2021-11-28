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
        val result = Either.Success(MovieDetailsTestProvider.movie1)
        val useCase = mock<MovieDetailsUseCase>{
            onBlocking { getMovieDetailsOf(MovieDetailsTestProvider.id1) }.doReturn(result)
        }
        val viewModel = MovieDetailsViewModel(useCase)

        runBlocking { viewModel.fetchMovieDetailsOf(MovieDetailsTestProvider.id1, Dispatchers.Unconfined) }

        assertMovieDetails(viewModel)
        assertFalse(viewModel.error.value)
    }

    private fun assertMovieDetails(viewModel: MovieDetailsViewModel) {
        assertEquals(MovieDetailsTestProvider.id1, viewModel.model.id)
        assertEquals(MovieDetailsTestProvider.title1, viewModel.model.title)
        assertEquals(MovieDetailsTestProvider.releaseYear1, viewModel.model.releaseYear)
        assertEquals(MovieDetailsTestProvider.tagline1, viewModel.model.tagline)
        assertEquals(MovieDetailsTestProvider.overview1, viewModel.model.overview)
    }

    @Test
    fun `Show error on failed movie details`() {
        val errorMessage = "error"
        val result = Either.Failure(ErrorEntity(errorMessage))
        val useCase = mock<MovieDetailsUseCase>{
            onBlocking { getMovieDetailsOf(MovieDetailsTestProvider.id1) }.doReturn(result)
        }
        val viewModel = MovieDetailsViewModel(useCase)

        runBlocking { viewModel.fetchMovieDetailsOf(MovieDetailsTestProvider.id1, Dispatchers.Unconfined) }

        assertTrue(viewModel.error.value)
    }
}