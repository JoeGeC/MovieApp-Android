package joe.barker.movieapp

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.movieapp.movieDetails.MediaDetailsModel
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
        val result = Either.Success(MediaTestProvider.movieDetails1)
        val useCase = mock<MovieDetailsUseCase>{
            onBlocking { getMovieDetailsOf(MediaTestProvider.id1) }.doReturn(result)
        }
        val viewModel = MovieDetailsViewModel(useCase)

        runBlocking { viewModel.fetchMovieDetailsOf(MediaTestProvider.id1, Dispatchers.Unconfined) }

        assertMovieDetails(viewModel.model)
        assertFalse(viewModel.error.value)
    }

    private fun assertMovieDetails(result: MediaDetailsModel) {
        assertEquals(MediaTestProvider.id1, result.id)
        assertEquals(MediaTestProvider.title1, result.title)
        assertEquals(MediaTestProvider.releaseYear1, result.releaseYear)
        assertEquals(MediaTestProvider.releaseDateAsString1, result.releaseDate)
        assertEquals(MediaTestProvider.tagline1, result.tagline)
        assertEquals(MediaTestProvider.overview1, result.overview)
        assertEquals(MediaTestProvider.posterPath1, result.posterPath)
        assertEquals(MediaTestProvider.score1, result.score)
        assertEquals(MediaTestProvider.backdropPath1, result.backdropPath)
    }

    @Test
    fun `Show error on failed movie details`() {
        val errorMessage = "error"
        val result = Either.Failure(ErrorEntity(errorMessage))
        val useCase = mock<MovieDetailsUseCase>{
            onBlocking { getMovieDetailsOf(MediaTestProvider.id1) }.doReturn(result)
        }
        val viewModel = MovieDetailsViewModel(useCase)

        runBlocking { viewModel.fetchMovieDetailsOf(MediaTestProvider.id1, Dispatchers.Unconfined) }

        assertTrue(viewModel.error.value)
    }
}