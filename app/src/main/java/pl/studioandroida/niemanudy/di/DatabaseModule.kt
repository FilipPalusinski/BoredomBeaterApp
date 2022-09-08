package pl.studioandroida.niemanudy.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.studioandroida.niemanudy.data.local.ActivityDao
import pl.studioandroida.niemanudy.data.local.ActivityDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideActivityDatabase(app: Application): ActivityDatabase {
        return Room.databaseBuilder(
            app,
            ActivityDatabase::class.java,
            "activitydb.db"
        ).build()
    }

    @Provides
    fun provideActivityDao(activityDatabase: ActivityDatabase):
            ActivityDao = activityDatabase.activityDao()
}