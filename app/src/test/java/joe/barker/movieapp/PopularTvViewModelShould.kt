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
import org.mockito.kotlin.*

class PopularTvViewModelShould : PopularListItemAsserter() {
    private val tvList = listOf(MediaTestProvider.tvDetails1, MediaTestProvider.tvDetails2)
    private val movieUseCase = mock<PopularMoviesUseCase>()

    @Test
    fun `Get popular TV from use case`(){
        val result = Either.Success(tvList)
        val tvUseCase = mock<PopularTvUseCase> {
            onBlocking { getPopularTvShows() }.doReturn(result)
        }
        val viewModel = PopularViewModel(movieUseCase, tvUseCase)

        runBlocking { viewModel.fetchTvShows(Dispatchers.Unconfined) }

        assertListItem(MediaTestProvider.popularTvModel1, viewModel.popularList?.get(0))
        assertListItem(MediaTestProvider.popularTvModel2, viewModel.popularList?.get(1))
        Assertions.assertFalse(viewModel.error.value)
        Assertions.assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun `Show error on failed TV request`() {
        val errorMessage = "error"
        val result = Either.Failure(ErrorEntity(errorMessage))
        val tvUseCase = mock<PopularTvUseCase> {
            onBlocking { getPopularTvShows() }.doReturn(result)
        }
        val viewModel = PopularViewModel(movieUseCase, tvUseCase)

        runBlocking { viewModel.fetchTvShows(Dispatchers.Unconfined) }

        Assertions.assertTrue(viewModel.error.value)
    }
}