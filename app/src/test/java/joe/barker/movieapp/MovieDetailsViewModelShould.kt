package joe.barker.movieapp

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.movieapp.movieDetails.MovieDetailsModel
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

        assertMovieDetails(viewModel.model)
        assertFalse(viewModel.error.value)
    }

    private fun assertMovieDetails(result: MovieDetailsModel) {
        assertEquals(MovieTestProvider.id1, result.id)
        assertEquals(MovieTestProvider.title1, result.title)
        assertEquals(MovieTestProvider.releaseYear1, result.releaseYear)
        assertEquals(MovieTestProvider.releaseDateAsString1, result.releaseDate)
        assertEquals(MovieTestProvider.tagline1, result.tagline)
        assertEquals(MovieTestProvider.overview1, result.overview)
        assertEquals(MovieTestProvider.posterPath1, result.posterPath)
        assertEquals(MovieTestProvider.score1, result.score)
        assertEquals(MovieTestProvider.backdropPath1, result.backdropPath)
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