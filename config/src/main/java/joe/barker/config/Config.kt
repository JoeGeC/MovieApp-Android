package joe.barker.config

import android.content.Context
import joe.barker.repository.repository.MovieDetailsRepositoryImpl
import joe.barker.domain.MovieDetailsUseCaseImpl
import joe.barker.local.LocalProvider
import joe.barker.remote.RemoteProvider

class Config(context: Context) {
    private val localProvider by lazy { LocalProvider(context) }
    private val remoteProvider by lazy { RemoteProvider() }

    private val movieDetailsRepository by lazy {
        MovieDetailsRepositoryImpl(localProvider.movieDetails, remoteProvider.movieDetails) }

    val movieDetailsUseCase by lazy { MovieDetailsUseCaseImpl(movieDetailsRepository) }
}