package joe.barker.domain

import joe.barker.domain.boundary.repository.PopularTvRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.useCase.PopularTvUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class PopularTvUseCaseShould {
    private val tvList = listOf(
        MediaDetailsUseCaseTestProvider.tvDetails1,
        MediaDetailsUseCaseTestProvider.tvDetails2
    )

    @Test
    fun `Get TV details from repository`(){
        val expected = Either.Success(tvList)
        val repository = mock<PopularTvRepository> {
            onBlocking { getPopularTvShows() } doReturn expected
        }
        val useCase = PopularTvUseCaseImpl(repository)

        val result = runBlocking { useCase.getPopularTvShows() }

        Assertions.assertEquals(expected, result)
    }
}