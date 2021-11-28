package joe.barker.domain

import joe.barker.domain.entity.MovieDetails
import java.time.LocalDate

class MovieDetailsUseCaseTestProvider {
    companion object{
        val movieDetails1 = MovieDetails(
            1,
            "title1",
            LocalDate.of(2021, 1, 1),
            "tagline1",
            "overview1",
            "posterId1",
            1.1f
        )
        val movieDetails2 = MovieDetails(
            2,
            "title2",
            LocalDate.of(2022, 1, 1),
            "tagline2",
            "overview2",
            "posterId2",
            2.2f
        )

    }
}