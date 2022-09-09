package pl.studioandroida.niemanudy.presentation.favourite

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.studioandroida.niemanudy.domain.model.Activity
import pl.studioandroida.niemanudy.domain.use_case.DeleteActivityFromFavUseCase
import pl.studioandroida.niemanudy.domain.use_case.GetActivitiesUseCase
import pl.studioandroida.niemanudy.util.Resource
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val deleteActivityFromFavUseCase: DeleteActivityFromFavUseCase

) : ViewModel() {

    private var _state = mutableStateOf(FavouriteListState())
    val state: State<FavouriteListState> = _state


    init {
        getActivities()
    }

    fun getActivities() {
        viewModelScope.launch {

            getActivitiesUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = result.data?.let { FavouriteListState(activities = it) }!!
                    }
                    is Resource.Error -> {
                        _state.value = FavouriteListState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = FavouriteListState(isLoading = true)
                    }
                }
            }
        }
    }

    fun deleteActivityFromFav(activity: Activity){
        viewModelScope.launch {
            deleteActivityFromFavUseCase(activity)
            getActivities()
        }
    }


}