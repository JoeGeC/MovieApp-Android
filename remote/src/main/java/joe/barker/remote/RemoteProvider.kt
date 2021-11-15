package joe.barker.remote

import joe.barker.remote.movieDetails.MovieDetailsRemoteImpl

class RemoteProvider {
    val movieDetails by lazy { MovieDetailsRemoteImpl() }
}