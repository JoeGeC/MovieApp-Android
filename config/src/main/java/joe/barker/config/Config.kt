package joe.barker.config

import joe.barker.data.repository.MovieDetailsRepositoryImpl
import joe.barker.domain.MovieDetailsUseCaseImpl

class Config {
    companion object{
        private val movieDetailsRepository by lazy { MovieDetailsRepositoryImpl() }

        val movieDetailsUseCase by lazy { MovieDetailsUseCaseImpl(movieDetailsRepository) }
    }

}