package pl.studioandroida.niemanudy.data.remote

import retrofit2.http.GET

interface ActivityService {
    @GET("/activity")
    suspend fun getActivities(): ActivityDto
}