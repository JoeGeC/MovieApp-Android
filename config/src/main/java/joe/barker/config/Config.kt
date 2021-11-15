package joe.barker.config

import joe.barker.domain.boundary.MovieDetailsRepository
import joe.barker.domain.movieDetails.MovieDetailsUseCase

interface Config {
    val movieDetailsRepository: MovieDetailsRepository

    val movieDetailsUseCase: MovieDetailsUseCase
}
