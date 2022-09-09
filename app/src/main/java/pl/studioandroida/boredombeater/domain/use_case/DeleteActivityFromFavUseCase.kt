package pl.studioandroida.boredombeater.domain.use_case

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.studioandroida.boredombeater.domain.model.Activity
import pl.studioandroida.boredombeater.domain.repository.ActivityRepository
import javax.inject.Inject


class DeleteActivityFromFavUseCase @Inject constructor(
    private val repository: ActivityRepository
){
    suspend operator fun invoke(activity: Activity) =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            try {
                repository.deleteActivity(activity)
            } catch (e: Exception) {
                Log.e("", "An unexpected error occured: $e")
            }
        }

}