package pl.studioandroida.niemanudy.data.repository

import pl.studioandroida.niemanudy.data.remote.ActivityDto
import pl.studioandroida.niemanudy.data.remote.ActivityService
import pl.studioandroida.niemanudy.data.remote.toActivity
import pl.studioandroida.niemanudy.domain.model.Activity
import pl.studioandroida.niemanudy.domain.repository.ActivityRepository
import pl.studioandroida.niemanudy.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val api: ActivityService
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

}