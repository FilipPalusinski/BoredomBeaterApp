package pl.studioandroida.niemanudy.domain.use_case

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
            emit(Resource.Loading<Activity>())
            val activity = repository.getActivity().toActivity()
            emit(Resource.Success<Activity>(activity))
        }catch (e: HttpException) {
            emit(Resource.Error<Activity>(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error<Activity>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
        }
    }

}