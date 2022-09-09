package pl.studioandroida.niemanudy.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.studioandroida.niemanudy.data.local.ActivityEntity
import pl.studioandroida.niemanudy.data.remote.ActivityDto
import pl.studioandroida.niemanudy.domain.model.Activity
import pl.studioandroida.niemanudy.util.Resource

interface ActivityRepository {

    suspend fun getActivity(): Resource<Activity>

    suspend fun insertActivity(activity: Activity)

    suspend fun deleteActivity(activity: Activity)

    suspend fun getActivities(): Resource<List<Activity>>

}