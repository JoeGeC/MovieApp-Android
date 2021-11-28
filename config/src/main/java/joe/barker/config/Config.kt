package joe.barker.config

import joe.barker.domain.boundary.repository.MovieDetailsRepository
import joe.barker.domain.boundary.useCase.MovieDetailsUseCase
import joe.barker.domain.useCase.PopularMoviesUseCaseImpl
import joe.barker.repository.repository.PopularMoviesRepositoryImpl

interface Config {
    val movieDetailsRepository: MovieDetailsRepository
    val movieDetailsUseCase: MovieDetailsUseCase
    val popularMoviesRepository: PopularMoviesRepositoryImpl
    val popularMoviesUseCase: PopularMoviesUseCaseImpl
}
