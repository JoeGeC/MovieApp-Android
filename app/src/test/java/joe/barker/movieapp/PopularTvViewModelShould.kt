package joe.barker.movieapp

import joe.barker.domain.boundary.useCase.PopularTvUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.movieapp.popular.PopularListItemModel
import joe.barker.movieapp.popular.PopularTvViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class PopularTvViewModelShould : PopularViewModelShould() {
    @Test
    fun `Get popular TV from use case`(){
        val result = Either.Success(mediaList)
        val tvUseCase = mock<PopularTvUseCase> {
            onBlocking { getPopularTvShows() }.doReturn(result)
        }
        val viewModel = PopularTvViewModel(tvUseCase)

        runBlocking { viewModel.fetchPopularTvShows(Dispatchers.Unconfined) }

        assertListItem(MediaTestProvider.popularListItemModel1, viewModel.popularList?.get(0))
        assertListItem(MediaTestProvider.popularListItemModel2, viewModel.popularList?.get(1))
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
        val viewModel = PopularTvViewModel(tvUseCase)

        runBlocking { viewModel.fetchPopularTvShows(Dispatchers.Unconfined) }

        Assertions.assertTrue(viewModel.error.value)
    }

    @Test
    fun `Do nothing if already has movies`(){
        val useCase = mock<PopularTvUseCase>()
        val viewModel = PopularTvViewModel(useCase)
        val movie = PopularListItemModel(1, "", "", 1.1f, "")
        viewModel.popularList = listOf(movie)

        runBlocking { viewModel.fetchPopularTvShows(Dispatchers.Unconfined) }

        verify(useCase, never()).getPopularTvShows()
        Assertions.assertEquals(listOf(movie), viewModel.popularList)
    }
}