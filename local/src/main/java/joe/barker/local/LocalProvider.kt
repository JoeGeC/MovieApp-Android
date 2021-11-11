package joe.barker.local

import android.content.Context
import androidx.room.Room
import joe.barker.local.moviedetails.MovieDetailsLocalImpl

class LocalProvider(context: Context) {
    private val database = Room.databaseBuilder(context, MovieDatabase::class.java, "moviedatabase").build()

    val movieDetailsLocal by lazy { MovieDetailsLocalImpl(database) }
}