package pl.studioandroida.niemanudy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.studioandroida.niemanudy.data.remote.ActivityDto

@Database(entities = [ActivityEntity::class], version = 1)
abstract class ActivityDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}