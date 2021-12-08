package joe.barker.domain

import joe.barker.domain.boundary.repository.MovieDetailsRepository
import joe.barker.domain.boundary.repository.TvDetailsRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.useCase.MovieDetailsUseCaseImpl
import joe.barker.domain.useCase.TvDetailsUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class TvDetailsUseCaseShould {
    @Test
    fun `Get movie details from repository`(){
        val expected = Either.Success(MediaDetailsUseCaseTestProvider.tvDetails1)
        val repository = mock<TvDetailsRepository> {
            onBlocking { getTvDetailsOf(1) } doReturn expected
        }
        val useCase = TvDetailsUseCaseImpl(repository)

        val result = runBlocking { useCase.getTvDetailsOf(1) }

        Assertions.assertEquals(expected, result)
    }
}