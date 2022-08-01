package pl.studioandroida.niemanudy.domain.repository

import pl.studioandroida.niemanudy.data.remote.ActivityDto
import pl.studioandroida.niemanudy.util.Resource

interface ActivityRepository {

    suspend fun getActivity(): ActivityDto
}