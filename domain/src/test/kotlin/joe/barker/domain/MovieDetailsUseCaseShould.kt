package joe.barker.domain

import joe.barker.domain.movieDetails.MovieDetailsUseCaseImpl
import joe.barker.domain.boundary.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.MovieDetails
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.LocalDate

class MovieDetailsUseCaseShould {
    @Test
    fun `Get movie details from repository`(){
        val movieDetails = MovieDetails(1, "title", LocalDate.of(2020, 1, 1), "tagline", "overview")
        val expected = Either.Success(movieDetails)
        val repository = mock<MovieDetailsRepository> {
            onBlocking { getMovieDetailsOf(1) } doReturn expected
        }
        val useCase = MovieDetailsUseCaseImpl(repository)

        val result = runBlocking { useCase.getMovieDetailsOf(1) }

        assertEquals(expected, result)
    }
}