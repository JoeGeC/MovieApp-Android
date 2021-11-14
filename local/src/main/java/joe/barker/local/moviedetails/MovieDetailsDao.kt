package joe.barker.local.moviedetails

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDetailsDao {
    @Query("SELECT * FROM moviedetails")
    fun getAll(): List<MovieDetails>

    @Query("SELECT * FROM moviedetails WHERE id IN (:movieIds)")
    fun loadAllByIds(movieIds: IntArray): List<MovieDetails>

    @Query("SELECT * FROM moviedetails WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): MovieDetails

    @Query("SELECT * FROM moviedetails WHERE id LIKE :id LIMIT 1")
    fun findById(id: Long): MovieDetails

    @Insert
    fun insertAll(vararg movieDetails: MovieDetails)

    @Delete
    fun delete(movieDetails: MovieDetails)
}