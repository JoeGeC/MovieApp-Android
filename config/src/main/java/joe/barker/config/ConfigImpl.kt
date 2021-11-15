package joe.barker.config

import android.content.Context
import joe.barker.repository.repository.MovieDetailsRepositoryImpl
import joe.barker.domain.movieDetails.MovieDetailsUseCaseImpl
import joe.barker.local.LocalProvider
import joe.barker.remote.RemoteProvider

class ConfigImpl(context: Context) : Config{
    private val localProvider by lazy { LocalProvider(context) }
    private val remoteProvider by lazy { RemoteProvider() }

    override val movieDetailsRepository by lazy {
        MovieDetailsRepositoryImpl(localProvider.movieDetails, remoteProvider.movieDetails) }

    override val movieDetailsUseCase by lazy { MovieDetailsUseCaseImpl(movieDetailsRepository) }
}