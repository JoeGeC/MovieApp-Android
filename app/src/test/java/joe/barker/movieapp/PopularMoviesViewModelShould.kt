package joe.barker.movieapp

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
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
    private val movieList = listOf(MovieDetailsTestProvider.movie1, MovieDetailsTestProvider.movie2)

    @Test
    fun `Get popular movies from use case`(){
        val result = Either.Success(movieList)
        val useCase = mock<PopularMoviesUseCase>{
            onBlocking { getPopularMovies() }.doReturn(result)
        }
        val viewModel = PopularMoviesViewModel(useCase)

        runBlocking { viewModel.fetchPopularMovies(Dispatchers.Unconfined) }

        assertFirstMovie(viewModel)
        assertSecondMovie(viewModel)
        Assertions.assertFalse(viewModel.error.value)
        Assertions.assertFalse(viewModel.isLoading.value)
    }

    private fun assertFirstMovie(viewModel: PopularMoviesViewModel) {
        Assertions.assertEquals(MovieDetailsTestProvider.id1, viewModel.movieList?.get(0)?.id)
        Assertions.assertEquals(MovieDetailsTestProvider.title1, viewModel.movieList?.get(0)?.title)
        Assertions.assertEquals(MovieDetailsTestProvider.releaseYear1, viewModel.movieList?.get(0)?.releaseYear)
        Assertions.assertEquals(MovieDetailsTestProvider.posterPath1, viewModel.movieList?.get(0)?.posterPath)
        Assertions.assertEquals(MovieDetailsTestProvider.score1, viewModel.movieList?.get(0)?.score)
    }

    private fun assertSecondMovie(viewModel: PopularMoviesViewModel) {
        Assertions.assertEquals(MovieDetailsTestProvider.id2, viewModel.movieList?.get(1)?.id)
        Assertions.assertEquals(MovieDetailsTestProvider.title2, viewModel.movieList?.get(1)?.title)
        Assertions.assertEquals(MovieDetailsTestProvider.releaseYear2, viewModel.movieList?.get(1)?.releaseYear)
        Assertions.assertEquals(MovieDetailsTestProvider.posterId2, viewModel.movieList?.get(1)?.posterPath)
        Assertions.assertEquals(MovieDetailsTestProvider.score2, viewModel.movieList?.get(1)?.score)
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