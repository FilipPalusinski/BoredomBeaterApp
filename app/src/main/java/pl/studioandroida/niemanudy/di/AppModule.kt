package pl.studioandroida.niemanudy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.studioandroida.niemanudy.data.local.ActivityDao
import pl.studioandroida.niemanudy.data.remote.ActivityService
import pl.studioandroida.niemanudy.data.repository.ActivityRepositoryImpl
import pl.studioandroida.niemanudy.domain.repository.ActivityRepository
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