package pl.studioandroida.boredombeater.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.studioandroida.boredombeater.domain.use_case.AddActivityToFavUseCase
import pl.studioandroida.boredombeater.domain.use_case.GetSingleActivityUseCase
import pl.studioandroida.boredombeater.util.Resource
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSingleActivityUseCase: GetSingleActivityUseCase,
    private val addActivityToFavUseCase: AddActivityToFavUseCase

) : ViewModel() {

    private val _state = mutableStateOf(ActivityState())
    val state: State<ActivityState> = _state


    init {
        getActivity()
    }

     fun getActivity(){
        getSingleActivityUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ActivityState(activity = result.data)
                }
                is Resource.Error -> {
                    _state.value = ActivityState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ActivityState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addActivityToFav(){
        viewModelScope.launch {
            val activity = state.value.activity
            Log.d("works", "Co to za: ${activity?.activity}")
            activity?.let {
                addActivityToFavUseCase(activity)
            }
        }
    }


    fun getCurrentDate(): String{
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")
        return sdf.format(Date())
    }

}