package joe.barker.domain

import joe.barker.domain.boundary.repository.PopularTvRepository
import joe.barker.domain.boundary.useCase.PopularTvUseCase
import joe.barker.domain.entity.Either
import joe.barker.domain.useCase.PopularMoviesUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class PopularTvUseCaseShould {
    private val mediaList = listOf(
        MediaDetailsUseCaseTestProvider.mediaDetails1,
        MediaDetailsUseCaseTestProvider.mediaDetails2
    )

    @Test
    fun `Get TV details from repository`(){
        val expected = Either.Success(mediaList)
        val repository = mock<PopularTvRepository> {
            onBlocking { getPopularTvShows() } doReturn expected
        }
        val useCase = PopularTvUseCaseImpl(repository)

        val result = runBlocking { useCase.getPopularTvShows() }

        Assertions.assertEquals(expected, result)
    }
}