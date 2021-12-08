package joe.barker.local

import android.content.Context
import androidx.room.Room
import joe.barker.local.movieDetails.MovieDetailsLocalImpl
import joe.barker.local.tvDetails.TvDetailsLocalImpl

class LocalProvider(context: Context) {
    private val movieDatabase = Room.databaseBuilder(context, MovieDatabase::class.java, "moviedatabase").build()
    private val tvDatabase = Room.databaseBuilder(context, TvDatabase::class.java, "tvdatabase").build()

    val movieDetails by lazy { MovieDetailsLocalImpl(movieDatabase) }
    val tvDetails by lazy { TvDetailsLocalImpl(tvDatabase) }
}