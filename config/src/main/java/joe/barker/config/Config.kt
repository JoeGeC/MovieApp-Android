package joe.barker.config

import joe.barker.domain.boundary.repository.MovieDetailsRepository
import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.domain.useCase.PopularMoviesUseCaseImpl
import joe.barker.domain.useCase.PopularTvUseCaseImpl
import joe.barker.repository.repository.PopularMoviesRepositoryImpl
import joe.barker.repository.repository.PopularTvRepositoryImpl

interface Config {
    val movieDetailsRepository: MovieDetailsRepository
    val movieDetailsUseCase: MovieDetailsUseCase
    val popularMoviesRepository: PopularMoviesRepositoryImpl
    val popularMoviesUseCase: PopularMoviesUseCaseImpl
    val popularTvRepository: PopularTvRepositoryImpl
    val popularTvUseCase: PopularTvUseCaseImpl
}
