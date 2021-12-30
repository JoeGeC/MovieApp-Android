package joe.barker.movieapp

import joe.barker.domain.boundary.useCase.PopularMoviesUseCase
import joe.barker.domain.boundary.useCase.PopularTvUseCase
import joe.barker.movieapp.popular.PopularViewModel
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyBlocking

class PopularViewModelShould {
    private val movieUseCase = mock<PopularMoviesUseCase>()
    private val tvUseCase = mock<PopularTvUseCase>()

    @Test
    fun `Fetch tv shows on switch`(){
        val viewModel = PopularViewModel(movieUseCase, tvUseCase)

        viewModel.onSwitch()

        verifyBlocking(tvUseCase, times(1), { getPopularTvShows() })
    }

    @Test
    fun `Fetch movies on switch`(){
        val viewModel = PopularViewModel(movieUseCase, tvUseCase)

        viewModel.onSwitch()
        viewModel.onSwitch()

        verifyBlocking(tvUseCase, times(1), { getPopularTvShows() })
        verifyBlocking(movieUseCase, times(1), { getPopularMovies() })
    }
}