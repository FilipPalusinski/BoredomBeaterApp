package pl.studioandroida.boredombeater.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity")
data class ActivityEntity(
    val activity: String,
    val type: String,
    val participants: Int,
    val link: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)


