package joe.barker.domain

import joe.barker.domain.boundary.repository.MovieDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.useCase.MovieDetailsUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class MovieDetailsUseCaseShould {
    @Test
    fun `Get movie details from repository`(){
        val expected = Either.Success(MovieDetailsUseCaseTestProvider.movieDetails1)
        val repository = mock<MovieDetailsRepository> {
            onBlocking { getMovieDetailsOf(1) } doReturn expected
        }
        val useCase = MovieDetailsUseCaseImpl(repository)

        val result = runBlocking { useCase.getMovieDetailsOf(1) }

        Assertions.assertEquals(expected, result)
    }
}