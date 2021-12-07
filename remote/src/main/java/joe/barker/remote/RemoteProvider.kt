package joe.barker.remote

import joe.barker.remote.movieDetails.MovieDetailsRemoteImpl
import joe.barker.remote.popularMovies.PopularMoviesRemoteImpl
import joe.barker.remote.popularTv.PopularTvShowsRemoteImpl

class RemoteProvider {
    val movieDetails by lazy { MovieDetailsRemoteImpl() }
    val popularMovies by lazy { PopularMoviesRemoteImpl() }
    val popularTvShows by lazy { PopularTvShowsRemoteImpl() }
}