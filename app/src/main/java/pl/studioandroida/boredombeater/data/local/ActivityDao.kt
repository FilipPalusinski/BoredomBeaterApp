package pl.studioandroida.boredombeater.data.local

import androidx.room.*

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activity")
    suspend fun getActivities(): List<ActivityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: ActivityEntity)

    @Delete
    suspend fun deleteActivity(activity: ActivityEntity)
}