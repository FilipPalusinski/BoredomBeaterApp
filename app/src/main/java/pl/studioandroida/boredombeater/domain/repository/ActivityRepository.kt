package pl.studioandroida.boredombeater.domain.repository

import pl.studioandroida.boredombeater.domain.model.Activity
import pl.studioandroida.boredombeater.util.Resource

interface ActivityRepository {

    suspend fun getActivity(): Resource<Activity>

    suspend fun insertActivity(activity: Activity)

    suspend fun deleteActivity(activity: Activity)

    suspend fun getActivities(): Resource<List<Activity>>

}