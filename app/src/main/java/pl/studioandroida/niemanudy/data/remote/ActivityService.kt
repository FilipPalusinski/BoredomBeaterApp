package pl.studioandroida.niemanudy.data.remote

import pl.studioandroida.niemanudy.util.Resource
import retrofit2.http.GET

interface ActivityService {
    @GET("/activity")
    suspend fun getActivity(): ActivityDto
}