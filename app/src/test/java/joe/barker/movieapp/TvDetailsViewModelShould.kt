package joe.barker.movieapp

import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.domain.boundary.useCase.TvDetailsUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.movieapp.details.MovieDetailsViewModel
import joe.barker.movieapp.details.TvDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class TvDetailsViewModelShould {
    @Test
    fun `Get tv details`() {
        val result = Either.Success(MediaTestProvider.tvDetails1)
        val useCase = mock<TvDetailsUseCase> {
            onBlocking { getTvDetailsOf(MediaTestProvider.id1) }.doReturn(result)
        }
        val viewModel = TvDetailsViewModel(useCase)

        runBlocking { viewModel.fetchDetailsOf(MediaTestProvider.id1, Dispatchers.Unconfined) }

        MediaTestProvider.assertMediaDetails(viewModel.model)
        Assertions.assertFalse(viewModel.error.value)
    }

    @Test
    fun `Show error on failed movie details`() {
        val errorMessage = "error"
        val result = Either.Failure(ErrorEntity(errorMessage))
        val useCase = mock<TvDetailsUseCase>{
            onBlocking { getTvDetailsOf(MediaTestProvider.id1) }.doReturn(result)
        }
        val viewModel = TvDetailsViewModel(useCase)

        runBlocking { viewModel.fetchDetailsOf(MediaTestProvider.id1, Dispatchers.Unconfined) }

        Assertions.assertTrue(viewModel.error.value)
    }
}