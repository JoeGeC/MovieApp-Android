package joe.barker.remote

import joe.barker.repository.response.MovieDetailsResponse

class MovieDetailsRemoteTestProvider {
    companion object{
        const val movieId1 : Long = 1
        const val movieId2 : Long = 2
        val response1 = MovieDetailsResponse(movieId1, "title", "tagline", "overview", "2022-01-02", "posterId", 1.1f, "backdropPath1")
        val response2 = MovieDetailsResponse(movieId2, "title2", "tagline2", "overview2", "2022-01-02", "posterId2", 1.2f, "backdropPath2")
        val responseList = listOf(response1, response2)
    }
}