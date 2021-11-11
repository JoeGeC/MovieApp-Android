package joe.barker.local

import androidx.room.Database
import androidx.room.RoomDatabase
import joe.barker.local.moviedetails.MovieDetails
import joe.barker.local.moviedetails.MovieDetailsDao

@Database(entities = [MovieDetails::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDetailsDao(): MovieDetailsDao
}