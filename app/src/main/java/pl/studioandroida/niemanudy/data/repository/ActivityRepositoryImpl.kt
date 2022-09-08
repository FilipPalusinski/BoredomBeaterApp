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
            Log.d("works", "Activity  ${activity.activity}")
            Log.d("works", "added  ${activity.toActivityEntity().activity}")


        } catch (e: Exception) {
            Log.d("works", "Could not insert data: $e")
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

//        }catch (e: HttpException) {
//            Resource.Error(e.localizedMessage ?: "An unexpected error occured")
//        }catch (e: IOException){
//            Resource.Error("Couldn't reach server. Check your internet connection")
//        }


    }