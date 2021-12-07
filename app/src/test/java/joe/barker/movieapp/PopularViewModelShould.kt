package joe.barker.movieapp

import joe.barker.movieapp.popular.PopularListItemModel
import org.junit.jupiter.api.Assertions

open class PopularViewModelShould {
    protected val mediaList = listOf(MediaTestProvider.mediaDetails1, MediaTestProvider.mediaDetails2)

    protected fun assertListItem(expected: PopularListItemModel, result: PopularListItemModel?) {
        Assertions.assertEquals(expected.id, result?.id)
        Assertions.assertEquals(expected.title, result?.title)
        Assertions.assertEquals(expected.releaseYear, result?.releaseYear)
        Assertions.assertEquals(expected.posterPath, result?.posterPath)
        Assertions.assertEquals(expected.score, result?.score)
    }
}
