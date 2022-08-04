package pl.studioandroida.niemanudy.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.studioandroida.niemanudy.domain.use_case.GetActivityUseCase
import pl.studioandroida.niemanudy.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase
    ) : ViewModel() {

    private val _state = mutableStateOf(ActivityState())
    val state: State<ActivityState> = _state


    init {
        getActivity()
    }

     fun getActivity(){
        getActivityUseCase().onEach { result ->
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


}