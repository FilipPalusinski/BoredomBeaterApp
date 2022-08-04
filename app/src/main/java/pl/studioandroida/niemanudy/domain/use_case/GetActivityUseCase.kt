package pl.studioandroida.niemanudy.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.studioandroida.niemanudy.data.remote.toActivity
import pl.studioandroida.niemanudy.domain.model.Activity
import pl.studioandroida.niemanudy.domain.repository.ActivityRepository
import pl.studioandroida.niemanudy.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
){
    operator fun invoke(): Flow<Resource<Activity>> = flow {
        try {
            emit(Resource.Loading())
            val activity = repository.getActivity()
            emit(activity)
        }catch (e: Exception) {
            emit(Resource.Error("An unexpected error occured"))
        }
    }

}