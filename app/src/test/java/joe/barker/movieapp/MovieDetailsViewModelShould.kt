package joe.barker.movieapp

import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails
import joe.barker.domain.movieDetails.MovieDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.LocalDate

class MovieDetailsViewModelShould {
    private val id: Long = 1
    private val title = "title"
    private val releaseDate = LocalDate.of(2020, 1, 2)
    private val releaseYear = "2020"
    private val tagline = "tagline"
    private val overview = "overview"
    private val movieDetails = MovieDetails(id, title, releaseDate, tagline, overview)

    @Test
    fun `Get movie details`() {
        val result = Either.Success(movieDetails)
        val useCase = mock<MovieDetailsUseCase>{
            onBlocking { getMovieDetailsOf(id) }.doReturn(result)
        }
        val viewModel = MovieDetailsViewModel(useCase)

        runBlocking { viewModel.getMovieDetailsOf(id, Dispatchers.Unconfined) }

        assertEquals(title, viewModel.title)
        assertEquals(releaseYear, viewModel.releaseYear)
        assertEquals(tagline, viewModel.tagline)
        assertEquals(overview, viewModel.overview)
        assertFalse(viewModel.error.value)
    }

    @Test
    fun `Show error on failed movie details`() {
        val errorMessage = "error"
        val result = Either.Failure(ErrorEntity(errorMessage))
        val useCase = mock<MovieDetailsUseCase>{
            onBlocking { getMovieDetailsOf(id) }.doReturn(result)
        }
        val viewModel = MovieDetailsViewModel(useCase)

        runBlocking { viewModel.getMovieDetailsOf(id, Dispatchers.Unconfined) }

        assertTrue(viewModel.error.value)
    }
}