package joe.barker.local.tvDetails

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TvDetailsDao {
    @Query("SELECT * FROM tvDetails WHERE id LIKE :id LIMIT 1")
    fun findById(id: Long): TvDetails?

    @Insert
    fun insertAll(vararg tvDetails: TvDetails)

    @Delete
    fun delete(tvDetails: TvDetails)
}