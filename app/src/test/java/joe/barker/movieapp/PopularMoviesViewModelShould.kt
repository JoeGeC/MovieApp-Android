package joe.barker.movieapp

import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.movieapp.popularMovies.PopularMovieModel
import joe.barker.movieapp.popularMovies.PopularMoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class PopularMoviesViewModelShould {
    private val movieList = listOf(MovieTestProvider.movie1, MovieTestProvider.movie2)

    @Test
    fun `Get popular movies from use case`(){
        val result = Either.Success(movieList)
        val useCase = mock<PopularMoviesUseCase>{
            onBlocking { getPopularMovies() }.doReturn(result)
        }
        val viewModel = PopularMoviesViewModel(useCase)

        runBlocking { viewModel.fetchPopularMovies(Dispatchers.Unconfined) }

        assertMovie(MovieTestProvider.movieModel1, viewModel.movieList?.get(0))
        assertMovie(MovieTestProvider.movieModel2, viewModel.movieList?.get(1))
        Assertions.assertFalse(viewModel.error.value)
        Assertions.assertFalse(viewModel.isLoading.value)
    }

    private fun assertMovie(expected: PopularMovieModel, result: PopularMovieModel?) {
        Assertions.assertEquals(expected.id, result?.id)
        Assertions.assertEquals(expected.title, result?.title)
        Assertions.assertEquals(expected.releaseYear, result?.releaseYear)
        Assertions.assertEquals(expected.posterPath, result?.posterPath)
        Assertions.assertEquals(expected.score, result?.score)
    }

    @Test
    fun `Show error on failed request`() {
        val errorMessage = "error"
        val result = Either.Failure(ErrorEntity(errorMessage))
        val useCase = mock<PopularMoviesUseCase>{
            onBlocking { getPopularMovies() }.doReturn(result)
        }
        val viewModel = PopularMoviesViewModel(useCase)

        runBlocking { viewModel.fetchPopularMovies(Dispatchers.Unconfined) }

        Assertions.assertTrue(viewModel.error.value)
    }

    @Test
    fun `Do nothing if already has movies`(){
        val useCase = mock<PopularMoviesUseCase>()
        val viewModel = PopularMoviesViewModel(useCase)
        val movie = PopularMovieModel(1, "", "", 1.1f, "")
        viewModel.movieList = listOf(movie)

        runBlocking { viewModel.fetchPopularMovies(Dispatchers.Unconfined) }

        verify(useCase, never()).getPopularMovies()
        Assertions.assertEquals(listOf(movie), viewModel.movieList)
    }
}