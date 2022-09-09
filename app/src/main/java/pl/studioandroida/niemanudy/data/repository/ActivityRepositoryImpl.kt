package pl.studioandroida.niemanudy.data.repository

import android.util.Log
import pl.studioandroida.niemanudy.data.local.ActivityDao
import pl.studioandroida.niemanudy.data.mapper.toActivity
import pl.studioandroida.niemanudy.data.mapper.toActivityEntity
import pl.studioandroida.niemanudy.data.remote.ActivityService
import pl.studioandroida.niemanudy.domain.model.Activity
import pl.studioandroida.niemanudy.domain.repository.ActivityRepository
import pl.studioandroida.niemanudy.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val api: ActivityService,
    private val activityDao: ActivityDao,

): ActivityRepository{


    override suspend fun getActivity(): Resource<Activity> {
        return try{
            val activity = api.getActivity()
            Resource.Success(activity.toActivity())
        }catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
        }catch (e: IOException){
            Resource.Error("Couldn't reach server. Check your internet connection")
        }
    }

    override suspend fun insertActivity(activity: Activity) {
        try{
            activityDao.insertActivity(activity.toActivityEntity())
        } catch (e: Exception) {
            Log.d("Exception", "Could not insert data: $e")
        }
    }

    override suspend fun deleteActivity(activity: Activity) {
        try{
            activityDao.deleteActivity(activity.toActivityEntity())
        } catch (e: Exception) {
            Log.d("Exception", "Could not delete data: $e")
        }
    }


        override suspend fun getActivities(): Resource<List<Activity>> {
        return try {
            val activities = activityDao.getActivities()
            Resource.Success(
                data = activities.map { it.toActivity() },
            )
        } catch (e: HttpException) {
                Resource.Error(e.localizedMessage ?: "An unexpected error occured")
            }catch (e: IOException){
                Resource.Error("Database Error")
            }
        }


    }