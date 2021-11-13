package joe.barker.remote

class RemoteProvider {
    val movieDetails by lazy { MovieDetailsRemoteImpl() }
}