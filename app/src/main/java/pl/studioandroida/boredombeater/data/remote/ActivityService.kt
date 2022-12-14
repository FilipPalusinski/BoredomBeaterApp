package pl.studioandroida.boredombeater.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers

interface ActivityService {
    @Headers("Content-Type: application/json")
    @GET("activity")
    suspend fun getActivity(): ActivityDto
}