package joe.barker.domain

import joe.barker.domain.boundary.repository.PopularMoviesRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.useCase.PopularMoviesUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class PopularMoviesUseCaseShould {
    private val movieList = listOf(
        MediaDetailsUseCaseTestProvider.movieDetails1,
        MediaDetailsUseCaseTestProvider.movieDetails2
    )

    @Test
    fun `Get movie details from repository`(){
        val expected = Either.Success(movieList)
        val repository = mock<PopularMoviesRepository> {
            onBlocking { getPopularMovies() } doReturn expected
        }
        val useCase = PopularMoviesUseCaseImpl(repository)

        val result = runBlocking { useCase.getPopularMovies() }

        Assertions.assertEquals(expected, result)
    }
}