package joe.barker.repository.repository

import joe.barker.domain.boundary.repository.PopularMoviesRepository
import joe.barker.domain.entity.Either
import joe.barker.domain.entity.ErrorEntity
import joe.barker.domain.entity.MediaDetails
import joe.barker.repository.adapter.convertMovieResponse
import joe.barker.repository.boundary.PopularMoviesRemote

class PopularMoviesRepositoryImpl(
    private val remote: PopularMoviesRemote
) : PopularMoviesRepository {

    override fun getPopularMovies(): Either<List<MediaDetails>?, ErrorEntity?> =
        remote.getPopularMovies().convertMovieResponse()
}


