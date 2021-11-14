package joe.barker.local.moviedetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import joe.barker.data.response.MovieDetailsResponse

@Entity
data class MovieDetails(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "tagline") val tagline: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "release_date") val release_date: String?
)