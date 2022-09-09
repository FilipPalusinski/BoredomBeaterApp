package pl.studioandroida.boredombeater.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.studioandroida.boredombeater.data.local.ActivityDao
import pl.studioandroida.boredombeater.data.remote.ActivityService
import pl.studioandroida.boredombeater.data.repository.ActivityRepositoryImpl
import pl.studioandroida.boredombeater.domain.repository.ActivityRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinRepository(api: ActivityService, dao: ActivityDao): ActivityRepository {
        return ActivityRepositoryImpl(api, dao)
    }

}