package joe.barker.movieapp

import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.movieapp.popular.PopularListItemModel
import joe.barker.movieapp.popular.PopularMoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class PopularMoviesViewModelShould : PopularViewModelShould() {
    private val movieList = listOf(MediaTestProvider.movieDetails1, MediaTestProvider.movieDetails2)

    @Test
    fun `Get popular movies from use case`(){
        val result = Either.Success(movieList)
        val movieUseCase = mock<PopularMoviesUseCase> {
            onBlocking { getPopularMovies() }.doReturn(result)
        }
        val viewModel = PopularMoviesViewModel(movieUseCase)

        runBlocking { viewModel.fetchPopular(Dispatchers.Unconfined) }

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
        val viewModel = PopularMoviesViewModel(useCase)

        runBlocking { viewModel.fetchPopular(Dispatchers.Unconfined) }

        Assertions.assertTrue(viewModel.error.value)
    }

    @Test
    fun `Do nothing if already has movies`(){
        val useCase = mock<PopularMoviesUseCase>()
        val viewModel = PopularMoviesViewModel(useCase)
        val movie = PopularListItemModel(1, "", "", 1.1f, "", "")
        viewModel.popularList = listOf(movie)

        runBlocking { viewModel.fetchPopular(Dispatchers.Unconfined) }

        verifyBlocking(useCase, never()) { getPopularMovies() }
        Assertions.assertEquals(listOf(movie), viewModel.popularList)
    }
}