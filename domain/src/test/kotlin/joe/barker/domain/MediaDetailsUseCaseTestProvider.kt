package joe.barker.domain

import joe.barker.domain.entity.MediaDetails
import joe.barker.domain.entity.MediaType
import java.time.LocalDate

class MediaDetailsUseCaseTestProvider {
    companion object{
        val movieDetails1 = MediaDetails(
            1,
            "title1",
            LocalDate.of(2021, 1, 1),
            "tagline1",
            "overview1",
            "posterId1",
            1.1f,
            "backdrop1.png",
            MediaType.Movie
        )
        val movieDetails2 = MediaDetails(
            2,
            "title2",
            LocalDate.of(2022, 1, 1),
            "tagline2",
            "overview2",
            "posterId2",
            2.2f,
            "backdrop2.png",
            MediaType.Movie
        )

        val tvDetails1 = MediaDetails(
            1,
            "title1",
            LocalDate.of(2021, 1, 1),
            "tagline1",
            "overview1",
            "posterId1",
            1.1f,
            "backdrop1.png",
            MediaType.Tv
        )
        val tvDetails2 = MediaDetails(
            2,
            "title2",
            LocalDate.of(2022, 1, 1),
            "tagline2",
            "overview2",
            "posterId2",
            2.2f,
            "backdrop2.png",
            MediaType.Tv
        )
    }
}