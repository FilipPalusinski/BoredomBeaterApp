package pl.studioandroida.boredombeater.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ActivityEntity::class], version = 1)
abstract class ActivityDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}