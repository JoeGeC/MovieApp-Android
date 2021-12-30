package joe.barker.movieapp

import joe.barker.movieapp.popular.PopularListItemModel
import org.junit.jupiter.api.Assertions

open class PopularListItemAsserter {
    protected fun assertListItem(expected: PopularListItemModel, result: PopularListItemModel?) {
        Assertions.assertEquals(expected.id, result?.id)
        Assertions.assertEquals(expected.title, result?.title)
        Assertions.assertEquals(expected.releaseYear, result?.releaseYear)
        Assertions.assertEquals(expected.posterPath, result?.posterPath)
        Assertions.assertEquals(expected.score, result?.score)
        Assertions.assertEquals(expected.type, result?.type)
    }
}
