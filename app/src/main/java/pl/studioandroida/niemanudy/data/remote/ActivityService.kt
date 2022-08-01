package pl.studioandroida.niemanudy.data.remote

import pl.studioandroida.niemanudy.util.Resource
import retrofit2.http.GET
import retrofit2.http.Headers

interface ActivityService {
    @Headers("Content-Type: application/json")
    @GET("activity")
    suspend fun getActivity(): ActivityDto
}