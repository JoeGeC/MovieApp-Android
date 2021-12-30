package joe.barker.movieapp

import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
import joe.barker.domain.boundary.useCase.PopularTvUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.movieapp.popular.PopularViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class PopularMoviesViewModelShould : PopularListItemAsserter() {
    private val movieList = listOf(MediaTestProvider.movieDetails1, MediaTestProvider.movieDetails2)
    private val tvUseCase = mock<PopularTvUseCase>()

    @Test
    fun `Get popular movies from use case`(){
        val result = Either.Success(movieList)
        val movieUseCase = mock<PopularMoviesUseCase> {
            onBlocking { getPopularMovies() }.doReturn(result)
        }
        val viewModel = PopularViewModel(movieUseCase, tvUseCase)

        runBlocking { viewModel.fetchMovies(Dispatchers.Unconfined) }

        assertListItem(MediaTestProvider.popularMovieModel1, viewModel.popularList?.get(0))
        assertListItem(MediaTestProvider.popularMovieModel2, viewModel.popularList?.get(1))
        Assertions.assertFalse(viewModel.error.value)
        Assertions.assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun `Show error on failed movies request`() {
        val errorMessage = "error"
        val result = Either.Failure(ErrorEntity(errorMessage))
        val useCase = mock<PopularMoviesUseCase>{
            onBlocking { getPopularMovies() }.doReturn(result)
        }
        val viewModel = PopularViewModel(useCase, tvUseCase)

        runBlocking { viewModel.fetchMovies(Dispatchers.Unconfined) }

        Assertions.assertTrue(viewModel.error.value)
    }
}