package joe.barker.remote

import joe.barker.repository.response.MovieDetailsResponse
import joe.barker.repository.response.TvDetailsResponse

class MediaDetailsRemoteTestProvider {
    companion object{
        const val id1 : Long = 1
        const val id2 : Long = 2
        val movieResponse1 = MovieDetailsResponse(id1, "title", "tagline", "overview", "2022-01-02", "posterId", 1.1f, "backdropPath1")
        val movieResponse2 = MovieDetailsResponse(id2, "title2", "tagline2", "overview2", "2022-01-02", "posterId2", 1.2f, "backdropPath2")
        val movieResponseList = listOf(movieResponse1, movieResponse2)
        val tvResponse1 = TvDetailsResponse(id1, "title", "tagline", "overview", "2022-01-02", "posterId", 1.1f, "backdropPath1")
        val tvResponse2 = TvDetailsResponse(id2, "title2", "tagline2", "overview2", "2022-01-02", "posterId2", 1.2f, "backdropPath2")
        val tvResponseList = listOf(tvResponse1, tvResponse2)
    }
}