package pl.studioandroida.boredombeater.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.studioandroida.boredombeater.domain.model.Activity
import pl.studioandroida.boredombeater.domain.repository.ActivityRepository
import pl.studioandroida.boredombeater.util.Resource
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