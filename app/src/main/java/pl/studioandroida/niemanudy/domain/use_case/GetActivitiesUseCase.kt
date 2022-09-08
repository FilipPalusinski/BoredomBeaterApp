package pl.studioandroida.niemanudy.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.studioandroida.niemanudy.domain.model.Activity
import pl.studioandroida.niemanudy.domain.repository.ActivityRepository
import pl.studioandroida.niemanudy.util.Resource
import javax.inject.Inject

class GetActivitiesUseCase @Inject constructor(
    private val repository: ActivityRepository
){


    operator fun invoke(): Flow<Resource<List<Activity>>> = flow {
        try {
            emit(Resource.Loading())
            val activity = repository.getActivities()
            emit(activity)
        }catch (e: Exception) {
            emit(Resource.Error("An unexpected error occured"))
        }
    }

}