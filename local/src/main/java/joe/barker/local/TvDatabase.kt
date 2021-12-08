package joe.barker.local

import androidx.room.Database
import androidx.room.RoomDatabase
import joe.barker.local.tvDetails.TvDetails
import joe.barker.local.tvDetails.TvDetailsDao

@Database(entities = [TvDetails::class], version = 1)
abstract class TvDatabase : RoomDatabase() {
    abstract fun tvDetailsDao(): TvDetailsDao
}
