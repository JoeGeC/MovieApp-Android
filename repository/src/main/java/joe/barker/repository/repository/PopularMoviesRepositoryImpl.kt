package joe.barker.repository.repository

import joe.barker.domain.boundary.repository.PopularMoviesRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MovieDetails
import joe.barker.repository.adapter.convert
import joe.barker.repository.boundary.PopularMoviesRemote
import joe.barker.repository.response.Result

class PopularMoviesRepositoryImpl(private val remote: PopularMoviesRemote) :
    PopularMoviesRepository {
    override fun getPopularMovies(): Either<List<MovieDetails>?, ErrorEntity?> {
        val response = remote.getPopularMovies()
        return if (response.isSuccess) {
            val success = Result.Success(response.body)
            Either.Success(success.value.convert())
        } else Either.Failure((response as Result.Failure).convert())
    }
}
