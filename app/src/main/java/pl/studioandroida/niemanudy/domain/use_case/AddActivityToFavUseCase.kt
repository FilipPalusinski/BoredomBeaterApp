package pl.studioandroida.niemanudy.domain.use_case


import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import pl.studioandroida.niemanudy.domain.model.Activity
import pl.studioandroida.niemanudy.domain.repository.ActivityRepository
import javax.inject.Inject

class AddActivityToFavUseCase @Inject constructor(
    private val repository: ActivityRepository
){
    operator fun invoke(activity: Activity) = CoroutineScope(IO).async {
        try {
            repository.insertActivity(activity)
        }catch (e: Exception) {
            Log.e("", "An unexpected error occured: $e")
        }
    }

}