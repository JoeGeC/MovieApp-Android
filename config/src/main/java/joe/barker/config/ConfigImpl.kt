package joe.barker.config

import android.content.Context
import joe.barker.repository.repository.MovieDetailsRepositoryImpl
import joe.barker.domain.useCase.MovieDetailsUseCaseImpl
import joe.barker.domain.useCase.PopularMoviesUseCaseImpl
import joe.barker.domain.useCase.PopularTvUseCaseImpl
import joe.barker.domain.useCase.TvDetailsUseCaseImpl
import joe.barker.local.LocalProvider
import joe.barker.remote.RemoteProvider
import joe.barker.repository.repository.PopularMoviesRepositoryImpl
import joe.barker.repository.repository.PopularTvRepositoryImpl
import joe.barker.repository.repository.TvDetailsRepositoryImpl

class ConfigImpl(context: Context) : Config{
    private val localProvider by lazy { LocalProvider(context) }
    private val remoteProvider by lazy { RemoteProvider() }

    override val movieDetailsRepository by lazy {
        MovieDetailsRepositoryImpl(localProvider.movieDetails, remoteProvider.movieDetails) }
    override val tvDetailsRepository by lazy {
        TvDetailsRepositoryImpl(localProvider.tvDetails, remoteProvider.tvDetails) }
    override val popularMoviesRepository by lazy {
        PopularMoviesRepositoryImpl(remoteProvider.popularMovies) }
    override val popularTvRepository by lazy {
        PopularTvRepositoryImpl(remoteProvider.popularTvShows) }

    override val movieDetailsUseCase by lazy { MovieDetailsUseCaseImpl(movieDetailsRepository) }
    override val tvDetailsUseCase by lazy { TvDetailsUseCaseImpl(tvDetailsRepository) }
    override val popularMoviesUseCase by lazy { PopularMoviesUseCaseImpl(popularMoviesRepository) }
    override val popularTvUseCase by lazy { PopularTvUseCaseImpl(popularTvRepository) }
}