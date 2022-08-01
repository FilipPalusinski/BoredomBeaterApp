package pl.studioandroida.niemanudy.data.repository

import pl.studioandroida.niemanudy.data.remote.ActivityDto
import pl.studioandroida.niemanudy.data.remote.ActivityService
import pl.studioandroida.niemanudy.domain.repository.ActivityRepository
import pl.studioandroida.niemanudy.util.Resource
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val api: ActivityService
): ActivityRepository{
    override suspend fun getActivity(): ActivityDto {
        return api.getActivity()
    }

}