package joe.barker.config

import android.content.Context
import joe.barker.data.repository.MovieDetailsRepositoryImpl
import joe.barker.domain.MovieDetailsUseCaseImpl
import joe.barker.local.LocalProvider

class Config(context: Context) {
    private val localProvider = LocalProvider(context)
    private val movieLocal by lazy { localProvider.movieDetailsLocal }

    private val movieDetailsRepository by lazy { MovieDetailsRepositoryImpl(movieLocal) }

    val movieDetailsUseCase by lazy { MovieDetailsUseCaseImpl(movieDetailsRepository) }
}